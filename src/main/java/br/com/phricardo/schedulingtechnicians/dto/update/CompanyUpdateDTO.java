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

    @Schema(description = "Email", example = "contato@empresaxyz.com.br")
    private String email;
    @Schema(description = "Phone", example = "+55 (11) 3765-4321")
    private String phone;
    @Schema(description = "Cellphone", example = "+55 (11) 93765-4321")
    private String cellphone;
    @Schema(description = "Address")
    private AddressResponseDTO address;
}
