package org.example.authorizationwithemail.security.repo;

import org.example.authorizationwithemail.security.entity.Role;
import org.example.authorizationwithemail.security.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(RoleName roleName);

}