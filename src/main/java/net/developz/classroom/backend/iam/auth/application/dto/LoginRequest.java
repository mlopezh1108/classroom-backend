package net.developz.classroom.backend.iam.auth.application.dto;

public record LoginRequest(
    String email,
    String password
) {}
