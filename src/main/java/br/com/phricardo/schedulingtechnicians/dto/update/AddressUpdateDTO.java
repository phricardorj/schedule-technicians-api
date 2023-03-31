package br.com.phricardo.schedulingtechnicians.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressUpdateDTO {

    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;
}
