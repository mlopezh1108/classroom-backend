package net.developz.classroom.backend.iam.auth.application.usecases;

import net.developz.classroom.backend.iam.auth.application.dto.AuthResponse;
import net.developz.classroom.backend.iam.auth.application.dto.LoginRequest;
import net.developz.classroom.backend.iam.auth.application.port.AuthenticationPort;
import net.developz.classroom.backend.iam.auth.application.port.TokenGeneratorPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginUseCaseTest {

    @Mock
    private AuthenticationPort authenticationPort;

    @Mock
    private TokenGeneratorPort tokenGeneratorPort;

    @InjectMocks
    private LoginUseCase useCase;

    @Test
    void shouldLoginAndReturnToken() {
        LoginRequest request = new LoginRequest("test@email.com", "password");
        when(tokenGeneratorPort.generateToken("test@email.com")).thenReturn("mock-token");

        AuthResponse result = useCase.execute(request);

        assertThat(result.accessToken()).isEqualTo("mock-token");
        verify(authenticationPort).authenticate("test@email.com", "password");
    }
}
