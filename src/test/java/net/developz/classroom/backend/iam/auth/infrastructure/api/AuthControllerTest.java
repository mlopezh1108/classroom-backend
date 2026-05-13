package net.developz.classroom.backend.iam.auth.infrastructure.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.developz.classroom.backend.iam.auth.application.dto.AuthResponse;
import net.developz.classroom.backend.iam.auth.application.dto.LoginRequest;
import net.developz.classroom.backend.iam.auth.application.usecases.LoginUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import net.developz.classroom.backend.iam.auth.infrastructure.security.jwt.JwtService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Objects.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private LoginUseCase loginUseCase;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private UserDetailsService userDetailsService;

    @Test
    void shouldLogin() throws Exception {
        LoginRequest request = new LoginRequest("test@email.com", "password");
        AuthResponse response = new AuthResponse("token-123");

        when(loginUseCase.execute(any(LoginRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(requireNonNull(MediaType.APPLICATION_JSON))
                .content(requireNonNull(objectMapper.writeValueAsString(request))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value("token-123"));
    }
}
