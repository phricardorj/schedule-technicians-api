package br.com.phricardo.schedulingtechnicians.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateDTO {

    private String email;
    private String phone;
    private String cellphone;
    private AddressUpdateDTO address;
}
