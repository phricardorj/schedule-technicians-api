package br.com.phricardo.schedulingtechnicians.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Company Response")
public class CompanyResponseDTO {

    private Long companyId;
    private String name;
    private String cnpj;
    private String email;
    private String phone;
    private String cellphone;
    private AddressResponseDTO address;
}
