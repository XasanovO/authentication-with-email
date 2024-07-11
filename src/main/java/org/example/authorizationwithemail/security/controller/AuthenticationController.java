package org.example.authorizationwithemail.security.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.authorizationwithemail.security.dto.LoginReq;
import org.example.authorizationwithemail.security.dto.LoginResponse;
import org.example.authorizationwithemail.security.dto.RegisterReq;
import org.example.authorizationwithemail.security.entity.User;
import org.example.authorizationwithemail.security.service.AuthenticationService;
import org.example.authorizationwithemail.security.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq loginReq) {
        User authenticatedUser = authenticationService.authenticate(loginReq.username(), loginReq.password());
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse("Bearer " + jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }

    @SneakyThrows
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterReq registerReq) {
        return authenticationService.register(registerReq);
    }

    @GetMapping("/activate")
    public ResponseEntity<?> activate(@RequestParam String token) {

        User user = authenticationService.activateAndSave(token);

        return ResponseEntity.ok(user);
    }


}
