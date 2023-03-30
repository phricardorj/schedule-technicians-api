package br.com.phricardo.schedulingtechnicians.dto;

import br.com.phricardo.schedulingtechnicians.validation.cpf.CPF;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

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
    private AddressDTO address;
}
