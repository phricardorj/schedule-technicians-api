package br.com.phricardo.schedulingtechnicians.dto.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Technician Update Request")
public class TechnicianUpdateDTO {

    private String email;
    private String phone;
    private String cellphone;
    private AddressUpdateDTO address;
}
