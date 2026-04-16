package net.developz.classroom.backend.iam.application.usecases;

import net.developz.classroom.backend.iam.application.dto.AuthResponse;
import net.developz.classroom.backend.iam.application.dto.LoginRequest;
import net.developz.classroom.backend.iam.application.port.AuthenticationPort;
import net.developz.classroom.backend.iam.application.port.TokenGeneratorPort;

public class LoginUseCase {
    private final AuthenticationPort authenticationPort;
    private final TokenGeneratorPort tokenGeneratorPort;

    public LoginUseCase(AuthenticationPort authenticationPort, TokenGeneratorPort tokenGeneratorPort) {
        this.authenticationPort = authenticationPort;
        this.tokenGeneratorPort = tokenGeneratorPort;
    }

    public AuthResponse execute(LoginRequest request) {
        authenticationPort.authenticate(request.email(), request.password());
        return new AuthResponse(tokenGeneratorPort.generateToken(request.email()));
    }
}
