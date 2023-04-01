package br.com.phricardo.schedulingtechnicians.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Customer Response")
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
