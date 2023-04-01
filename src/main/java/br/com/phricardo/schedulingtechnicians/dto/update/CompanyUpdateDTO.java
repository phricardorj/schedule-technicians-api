package br.com.phricardo.schedulingtechnicians.dto.update;

import br.com.phricardo.schedulingtechnicians.dto.response.AddressResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyUpdateDTO {

    private String email;
    private String phone;
    private String cellphone;
    private AddressResponseDTO address;
}
