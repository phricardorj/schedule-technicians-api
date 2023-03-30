package br.com.phricardo.schedulingtechnicians.dto;

import br.com.phricardo.schedulingtechnicians.validation.cpf.CPF;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnicianDTO {

    @NotBlank
    private String name;
    @NotBlank @CPF
    private String cpf;
    @NotNull @Past
    private LocalDate dateOfBirth;
    @NotBlank
    private String phone;
    @NotBlank
    private String cellphone;
    @Valid
    private AddressDTO address;
}
