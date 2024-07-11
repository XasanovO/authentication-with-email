package org.example.authorizationwithemail.security.dto;

public record  LoginResponse(String token, Long expiresInSeconds) {

}
