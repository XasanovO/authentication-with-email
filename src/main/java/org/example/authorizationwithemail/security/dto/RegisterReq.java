package org.example.authorizationwithemail.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterReq(
        @NotBlank(message = "Must not be Empty") String firstname,
        @NotBlank(message = "Must not be Empty") String lastname,
        @Email(message = "Please enter a valid email") @NotNull(message = "Email cannot be NULL") String username,
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Password should be at least 8 characters long and include at least one uppercase letter, one lowercase letter, and one digit.") String password,
        String repeatPassword
) {
}
