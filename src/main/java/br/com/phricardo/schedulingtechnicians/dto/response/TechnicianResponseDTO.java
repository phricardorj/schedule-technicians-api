package br.com.phricardo.schedulingtechnicians.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Technician Response")
public class TechnicianResponseDTO {

    @Schema(description = "Enrollment", example = "79487")
    private Long enrollment;
    @Schema(description = "Name", example = "João Silva")
    private String name;
    @Schema(description = "CPF", example = "123.456.789-10")
    private String cpf;
    @Schema(description = "Date Of Birth", example = "1980-01-01")
    private LocalDate dateOfBirth;
    @Schema(description = "Email", example = "joao.silva@exemplo.com")
    private String email;
    @Schema(description = "Phone", example = "+55 (11) 3765-4321")
    private String phone;
    @Schema(description = "Cellphone", example = "+55 (11) 93765-4321")
    private String cellphone;
    @Schema(description = "Address")
    private AddressResponseDTO address;
}
