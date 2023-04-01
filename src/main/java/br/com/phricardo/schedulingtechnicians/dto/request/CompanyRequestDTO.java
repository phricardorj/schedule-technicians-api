package br.com.phricardo.schedulingtechnicians.dto.request;

import br.com.phricardo.schedulingtechnicians.validation.cnpj.CNPJ;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Company Request")
public class CompanyRequestDTO {

    @NotBlank
    @Schema(description = "Name", example = "Empresa XYZ")
    private String name;

    @NotBlank
    @CNPJ
    @Schema(description = "CNPJ", example = "27.625.852/0001-42")
    private String cnpj;

    @NotBlank
    @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")
    @Schema(description = "Email", example = "contato@empresaxyz.com.br")
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\+55\\s\\(\\d{2}\\)\\s\\d{4}\\-\\d{4}$")
    @Schema(description = "Phone", example = "+55 (11) 3765-4321")
    private String phone;

    @NotBlank
    @Pattern(regexp = "^\\+55\\s\\(\\d{2}\\)\\s\\d{5}\\-\\d{4}$")
    @Schema(description = "Cellphone", example = "+55 (11) 93765-4321")
    private String cellphone;

    @Valid
    @Schema(description = "Address")
    private AddressRequestDTO address;
}
