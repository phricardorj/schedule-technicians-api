package br.com.phricardo.schedulingtechnicians.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Customer Response")
public class CustomerResponseDTO {

    @Schema(description = "Customer Id", example = "10")
    private Long customerId;
    @Schema(description = "Name", example = "Pedro Ricardo")
    private String name;
    @Schema(description = "CPF", example = "123.456.789-10")
    private String cpf;
    @Schema(description = "Date Of Birth", example = "1985-01-01")
    private LocalDate dateOfBirth;
    @Schema(description = "Email", example = "pedro.ricardo@example.com")
    private String email;
    @Schema(description = "Phone", example = "+55 (11) 3765-4321")
    private String phone;
    @Schema(description = "Cellphone", example = "+55 (11) 93765-4321")
    private String cellphone;
    @Schema(description = "Address")
    private AddressResponseDTO address;
}