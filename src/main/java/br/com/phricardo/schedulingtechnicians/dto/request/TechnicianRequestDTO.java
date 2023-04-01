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
    private String name;
    @NotBlank @CPF
    private String cpf;
    @NotNull @Past
    private LocalDate dateOfBirth;
    @NotBlank @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")
    private String email;
    @NotBlank
    private String phone;
    @NotBlank
    private String cellphone;
    @Valid
    private AddressRequestDTO address;
}
