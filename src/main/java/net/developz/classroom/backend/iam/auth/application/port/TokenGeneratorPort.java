package net.developz.classroom.backend.iam.auth.application.port;

public interface TokenGeneratorPort {
    String generateToken(String email);
}



