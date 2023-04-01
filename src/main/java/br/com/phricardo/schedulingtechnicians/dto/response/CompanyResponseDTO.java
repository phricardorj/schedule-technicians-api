package br.com.phricardo.schedulingtechnicians.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Company Response")
public class CompanyResponseDTO {

    @Schema(description = "Company Id", example = "20")
    private Long companyId;
    @Schema(description = "Name", example = "Empresa XYZ")
    private String name;
    @Schema(description = "CNPJ", example = "27.625.852/0001-42")
    private String cnpj;
    @Schema(description = "Email", example = "contato@empresaxyz.com.br")
    private String email;
    @Schema(description = "Phone", example = "+55 (11) 3765-4321")
    private String phone;
    @Schema(description = "Cellphone", example = "+55 (11) 93765-4321")
    private String cellphone;
    @Schema(description = "Address")
    private AddressResponseDTO address;
}
