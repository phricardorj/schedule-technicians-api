package br.com.phricardo.schedulingtechnicians.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Auth Login Request")
public class UserAuthLoginRequestDTO {

    @NotBlank(message = "login is required")
    @Schema(description = "Email or CPF", example = "contato@empresaxyz.com.br or 122.333.442-42")
    private String login;

    @NotBlank(message = "password is required")
    @Schema(description = "Password", example = "Ab*1example")
    private String password;
}
