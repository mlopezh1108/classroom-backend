package net.developz.classroom.backend.iam.auth.infrastructure.api;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.iam.auth.application.dto.AuthResponse;
import net.developz.classroom.backend.iam.auth.application.dto.LoginRequest;
import net.developz.classroom.backend.iam.auth.application.usecases.LoginUseCase;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final LoginUseCase loginUseCase;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ok(loginUseCase.execute(request));
    }
}



