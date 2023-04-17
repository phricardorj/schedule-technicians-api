package br.com.phricardo.schedulingtechnicians.controller;

import br.com.phricardo.schedulingtechnicians.dto.request.AuthLoginRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.request.AuthRegisterRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.TokenResponseDTO;
import br.com.phricardo.schedulingtechnicians.model.user.User;
import br.com.phricardo.schedulingtechnicians.service.AuthenticationService;
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

    private final AuthenticationService service;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public User register(@RequestBody @Valid AuthRegisterRequestDTO registerRequestDTO) {
        return service.registerUser(registerRequestDTO);
    }

    @PostMapping("/login")
    public TokenResponseDTO login(@RequestBody @Valid AuthLoginRequestDTO loginRequestDTO) {
        return service.loginUser(loginRequestDTO, authenticationManager);
    }
}
