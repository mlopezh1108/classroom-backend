package net.developz.classroom.backend.iam.auth.application.port;

public interface AuthenticationPort {
    void authenticate(String email, String password);
}



