package net.developz.classroom.backend.iam.application.port;

public interface TokenGeneratorPort {
    String generateToken(String email);
}
