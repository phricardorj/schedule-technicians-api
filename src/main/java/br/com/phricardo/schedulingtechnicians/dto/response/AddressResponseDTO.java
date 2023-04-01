package br.com.phricardo.schedulingtechnicians.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Address Response")
public class AddressResponseDTO {

    @Schema(description = "Public place", example = "Avenida Paulista")
    private String street;
    @Schema(description = "District", example = "Bela Vista")
    private String neighborhood;
    @Schema(description = "City", example = "São Paulo")
    private String city;
    @Schema(description = "State", example = "São Paulo")
    private String state;
    @Schema(description = "Zip code in Brazilian standard", example = "01310-100")
    private String zipCode;
}
