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
    private String name;
    @NotBlank @CNPJ
    private String cnpj;
    @NotBlank @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")
    private String email;
    @NotBlank
    private String phone;
    @NotBlank
    private String cellphone;
    @Valid
    private AddressRequestDTO address;
}
