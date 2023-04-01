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
@Schema(title = "Technician Request")
public class TechnicianRequestDTO {

    @NotBlank
    @Schema(description = "Name", example = "João Silva")
    private String name;

    @NotBlank
    @CPF
    @Schema(description = "CPF", example = "123.456.789-10")
    private String cpf;

    @NotNull
    @Past
    @Schema(description = "Date Of Birth", example = "1980-01-01")
    private LocalDate dateOfBirth;

    @NotBlank
    @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")
    @Schema(description = "Email", example = "joao.silva@exemplo.com")
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\+55\\s\\(\\d{2}\\)\\s\\d{4}\\-\\d{4}$")
    @Schema(description = "Phone", example = "+55 (11) 3765-4321")
    private String phone;

    @NotBlank
    @Pattern(regexp = "^\\+55\\s\\(\\d{2}\\)\\s\\d{5}\\-\\d{4}$")
    @Schema(description = "Cellphone", example = "+55 (11) 93765-4321")
    private String cellphone;

    @Valid
    @Schema(description = "Address")
    private AddressRequestDTO address;
}
