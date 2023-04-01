package br.com.phricardo.schedulingtechnicians.dto.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Address Update Request")
public class AddressUpdateDTO {

    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;
}
