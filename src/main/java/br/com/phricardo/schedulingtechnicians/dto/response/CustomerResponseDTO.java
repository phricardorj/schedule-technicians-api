package br.com.phricardo.schedulingtechnicians.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {

    private Long customerId;
    private String name;
    private String cpf;
    private LocalDate dateOfBirth;
    private String email;
    private String phone;
    private String cellphone;
    private AddressResponseDTO address;
}
