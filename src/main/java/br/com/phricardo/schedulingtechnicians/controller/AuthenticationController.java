package br.com.phricardo.schedulingtechnicians.controller;

import br.com.phricardo.schedulingtechnicians.dto.request.UserAuthLoginRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.request.UserAuthRegisterRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.TokenResponseDTO;
import br.com.phricardo.schedulingtechnicians.model.User;
import br.com.phricardo.schedulingtechnicians.service.UserAuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/v1/auth")
@AllArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user authentication and registration")
public class AuthenticationController {

    private final UserAuthenticationService service;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public User register(@RequestBody @Valid UserAuthRegisterRequestDTO registerRequestDTO) {
        return service.registerUser(registerRequestDTO);
    }

    @PostMapping("/login")
    public TokenResponseDTO login(@RequestBody @Valid UserAuthLoginRequestDTO loginRequestDTO) {
        return service.loginUser(loginRequestDTO, authenticationManager);
    }
}
