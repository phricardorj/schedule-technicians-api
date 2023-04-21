package br.com.phricardo.schedulingtechnicians.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Auth Register Request")
public class UserAuthRegisterRequestDTO {

    @NotBlank(message = "email is required")
    @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", message = "login needs to be a valid email")
    @Schema(description = "Email", example = "contato@empresaxyz.com.br")
    private String login;

    @NotBlank(message = "password is required")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+])[a-zA-Z\\d!@#$%^&*()_+]{8,}$", message = "password invalid")
    @Schema(description = "Password", example = "Ab*1example")
    private String password;
}
