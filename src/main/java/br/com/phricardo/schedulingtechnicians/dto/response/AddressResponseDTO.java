package br.com.phricardo.schedulingtechnicians.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseDTO {

    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;
}
