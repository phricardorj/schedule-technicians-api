package br.com.phricardo.schedulingtechnicians.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Address Request")
public class AddressRequestDTO {

    @NotBlank
    @Schema(description = "Public place", example = "Avenida Paulista")
    private String street;

    @NotBlank
    @Schema(description = "District", example = "Bela Vista")
    private String neighborhood;

    @NotBlank
    @Schema(description = "City", example = "São Paulo")
    private String city;

    @NotBlank
    @Schema(description = "State", example = "São Paulo")
    private String state;

    @NotBlank
    @Pattern(regexp = "^\\d{5}\\-\\d{3}$")
    @Schema(description = "Zip code in Brazilian standard", example = "01310-100")
    private String zipCode;
}