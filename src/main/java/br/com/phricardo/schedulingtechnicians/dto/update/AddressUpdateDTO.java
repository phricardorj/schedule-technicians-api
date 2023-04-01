package br.com.phricardo.schedulingtechnicians.dto.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Address Update Request")
public class AddressUpdateDTO {


    @Schema(description = "Address", example = "Avenida Paulista")
    private String address;
    @Schema(description = "Address number", example = "207")
    private Integer number;
    @Schema(description = "Address complement", example = "Apartment 104")
    private String complement;
    @Schema(description = "Neighborhood", example = "Bela Vista")
    private String neighborhood;
    @Schema(description = "City", example = "São Paulo")
    private String city;
    @Schema(description = "State", example = "São Paulo")
    private String state;
    @Schema(description = "Zip code in Brazilian standard", example = "01310-100")
    private String zipCode;
}
