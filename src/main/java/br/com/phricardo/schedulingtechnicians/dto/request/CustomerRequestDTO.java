package br.com.phricardo.schedulingtechnicians.dto.request;

import br.com.phricardo.schedulingtechnicians.validation.cpf.CPF;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Customer Request")
public class CustomerRequestDTO {

    @NotBlank(message = "name is required")
    @Schema(description = "Name", example = "Pedro Ricardo")
    private String name;

    @CPF
    @NotBlank(message = "cpf is required")
    @Schema(description = "CPF", example = "123.456.789-10")
    private String cpf;

    @Past(message = "the date of birth must be in the past")
    @NotNull(message = "date of birth is required")
    @Schema(description = "Date Of Birth", example = "1985-01-01")
    private LocalDate dateOfBirth;

    @NotBlank(message = "email is required")
    @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", message = "email invalid")
    @Schema(description = "Email", example = "pedro@empresaxyz.com.br")
    private String email;

    @NotBlank(message = "phone is required")
    @Pattern(regexp = "^\\+55\\s\\(\\d{2}\\)\\s\\d{4}\\-\\d{4}$", message = "phone invalid, correct format is +55 (XX) XXXX-XXXX")
    @Schema(description = "Phone", example = "+55 (11) 3765-4321")
    private String phone;

    @NotBlank(message = "cellphone is required")
    @Pattern(regexp = "^\\+55\\s\\(\\d{2}\\)\\s\\d{5}\\-\\d{4}$", message = "cellphone invalid, correct format is +55 (XX) XXXXX-XXXX")
    @Schema(description = "Cellphone", example = "+55 (11) 93765-4321")
    private String cellphone;

    @Valid
    @Schema(description = "Address")
    private AddressRequestDTO address;
}
