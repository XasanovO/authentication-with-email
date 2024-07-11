package org.example.authorizationwithemail.security.service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.example.authorizationwithemail.security.dto.RegisterReq;
import org.example.authorizationwithemail.security.entity.Role;
import org.example.authorizationwithemail.security.entity.RoleName;
import org.example.authorizationwithemail.security.entity.User;
import org.example.authorizationwithemail.security.mapping.UserMapper;
import org.example.authorizationwithemail.security.repo.RoleRepository;
import org.example.authorizationwithemail.security.repo.UserRepository;
import org.example.authorizationwithemail.services.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;


    public User authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return userRepository.findByUsername(username).orElse(null);
    }


    public ResponseEntity<?> register(RegisterReq registerReq) throws MessagingException {

        Optional<User> optionalUser = userRepository.findByUsername(registerReq.username());

        if (optionalUser.isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        if (!registerReq.password().equals(registerReq.repeatPassword())) {
            return ResponseEntity.badRequest().body("Passwords do not match");
        }

        User user = UserMapper.INSTANCE.toUser(registerReq);

        Map<String, Object> claims = Map.of(
                "firstname", user.getFirstname(),
                "lastname", user.getLastname(),
                "username", user.getUsername(),
                "password", user.getPassword()
        );

        String token = jwtService.generateToken(claims, user);
        String username = user.getUsername();

        emailService.sendConfirmLink(username, token);

        return ResponseEntity.ok(" Successfully confirm by email: ***%s@gmail.com"
                .formatted(
                        username.substring(username.length() - 13, username.length() - 10)
                )
        );
    }


    public User activateAndSave(String token) {
        Role roleUser = roleRepository.findByRole(RoleName.ROLE_USER);
        User user = jwtService.extractClaim(token, claims -> User.builder()
                .firstname(claims.get("firstname").toString())
                .lastname(claims.get("lastname").toString())
                .username(claims.get("username").toString())
                .password(passwordEncoder.encode(claims.get("password").toString()))
                .roles(List.of(roleUser))
                .build()
        );
        return userRepository.save(user);
    }
}
