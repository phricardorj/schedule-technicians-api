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

    @NotBlank(message = "name is required")
    @Schema(description = "Name", example = "Empresa XYZ")
    private String name;

    @CNPJ
    @NotBlank(message = "cnpj is required")
    @Schema(description = "CNPJ", example = "27.625.852/0001-42")
    private String cnpj;

    @NotBlank(message = "email is required")
    @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", message = "email invalid")
    @Schema(description = "Email", example = "contato@empresaxyz.com.br")
    private String email;

    @NotBlank(message = "phone is required")
    @Pattern(regexp = "^\\+55\\s\\(\\d{2}\\)\\s\\d{4}\\-\\d{4}$", message = "phone invalid, correct format is +55 (XX) XXXX-XXXX")
    @Schema(description = "Phone", example = "+55 (11) 3765-4321")
    private String phone;

    @NotBlank(message = "cellphone is required")
    @Pattern(regexp = "^\\+55\\s\\(\\d{2}\\)\\s\\d{5}\\-\\d{4}$", message = "cellphone invalid, correct format is +55 (XX) XXXXX-XXXX")
    @Schema(description = "Cellphone", example = "+55 (11) 93765-4321")
    private String cellphone;

    @Valid
    @Schema(description = "Address")
    private AddressRequestDTO address;
}
