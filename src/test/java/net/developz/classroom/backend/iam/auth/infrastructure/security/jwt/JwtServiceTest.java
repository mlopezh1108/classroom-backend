package net.developz.classroom.backend.iam.auth.infrastructure.security.jwt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Base64;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {

    @Mock
    private JwtProperties jwtProperties;

    private JwtService jwtService;

    private final String secretKey = Base64.getEncoder().encodeToString("super-secret-key-that-is-long-enough-for-hmac-sha-256".getBytes());

    @BeforeEach
    void setUp() {
        when(jwtProperties.getSecretKey()).thenReturn(secretKey);
        when(jwtProperties.getExpiration()).thenReturn(3600000L);
        jwtService = new JwtService(jwtProperties);
    }

    @Test
    void shouldGenerateAndExtractUsername() {
        String email = "test@user.com";
        String token = jwtService.generateToken(email);

        String username = jwtService.extractUsername(token);

        assertThat(username).isEqualTo(email);
    }

    @Test
    void shouldValidateToken() {
        String email = "test@user.com";
        String token = jwtService.generateToken(email);
        UserDetails userDetails = new User(email, "pass", Collections.emptyList());

        boolean isValid = jwtService.isTokenValid(token, userDetails);

        assertThat(isValid).isTrue();
    }
}
