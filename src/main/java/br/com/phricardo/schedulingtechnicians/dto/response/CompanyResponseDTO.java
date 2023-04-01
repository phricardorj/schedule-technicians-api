package br.com.phricardo.schedulingtechnicians.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponseDTO {

    private Long companyId;
    private String name;
    private String cnpj;
    private String email;
    private String phone;
    private String cellphone;
    private AddressResponseDTO address;
}
