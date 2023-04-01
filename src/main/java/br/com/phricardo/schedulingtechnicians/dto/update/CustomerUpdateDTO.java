package br.com.phricardo.schedulingtechnicians.dto.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Customer Update Request")
public class CustomerUpdateDTO {

    @Schema(description = "Email", example = "pedro.ricardo@example.com")
    private String email;
    @Schema(description = "Phone", example = "+55 (11) 3765-4321")
    private String phone;
    @Schema(description = "Cellphone", example = "+55 (11) 93765-4321")
    private String cellphone;
    @Schema(description = "Address")
    private AddressUpdateDTO address;
}