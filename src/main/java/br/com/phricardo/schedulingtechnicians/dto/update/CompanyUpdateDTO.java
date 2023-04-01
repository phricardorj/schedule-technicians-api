package br.com.phricardo.schedulingtechnicians.dto.update;

import br.com.phricardo.schedulingtechnicians.dto.response.AddressResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Company Update Request")
public class CompanyUpdateDTO {

    private String email;
    private String phone;
    private String cellphone;
    private AddressResponseDTO address;
}
