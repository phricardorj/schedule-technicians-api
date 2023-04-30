package br.com.phricardo.schedulingtechnicians.dto.request;

import br.com.phricardo.schedulingtechnicians.validation.cpf.CPF;
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
    private String email;

    @CPF
    @NotBlank(message = "cpf is required")
    @Schema(description = "CPF - Tax ID Number (Brazil)", example = "121.121.127.12")
    private String cpf;

    @NotBlank(message = "password is required")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+])[a-zA-Z\\d!@#$%^&*()_+]{8,}$", message = "password invalid")
    @Schema(description = "Password", example = "Ab*1example")
    private String password;

    @NotBlank(message = "role is required")
    @Pattern(regexp = "^(CUSTOMER|TECHNICIAN|MANAGER)$",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Invalid value. Accepted values: CUSTOMER, TECHNICIAN, MANAGER.")
    private String role;
}