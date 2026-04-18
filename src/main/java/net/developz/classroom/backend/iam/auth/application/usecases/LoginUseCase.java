package net.developz.classroom.backend.iam.auth.application.usecases;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.iam.auth.application.dto.AuthResponse;
import net.developz.classroom.backend.iam.auth.application.dto.LoginRequest;
import net.developz.classroom.backend.iam.auth.application.port.AuthenticationPort;
import net.developz.classroom.backend.iam.auth.application.port.TokenGeneratorPort;
import net.developz.classroom.backend.shared.application.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class LoginUseCase {
    private final AuthenticationPort authenticationPort;
    private final TokenGeneratorPort tokenGeneratorPort;

    public AuthResponse execute(LoginRequest request) {
        authenticationPort.authenticate(request.email(), request.password());
        return new AuthResponse(tokenGeneratorPort.generateToken(request.email()));
    }
}



