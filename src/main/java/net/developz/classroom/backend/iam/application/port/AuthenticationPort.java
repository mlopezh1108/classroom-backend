package net.developz.classroom.backend.iam.application.port;

public interface AuthenticationPort {
    void authenticate(String email, String password);
}
