package org.example.authorizationwithemail.security.config;

import lombok.RequiredArgsConstructor;
import org.example.authorizationwithemail.security.entity.Role;
import org.example.authorizationwithemail.security.entity.RoleName;
import org.example.authorizationwithemail.security.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Override
    public void run(String... args) {
        if (ddl.equals("create")) {
            Role role = new Role(1, RoleName.ROLE_USER);
            roleRepository.save(role);
        }
    }
}
