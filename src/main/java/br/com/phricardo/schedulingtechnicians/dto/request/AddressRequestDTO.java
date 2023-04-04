package br.com.phricardo.schedulingtechnicians.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Address Request")
public class AddressRequestDTO {

    @NotBlank(message = "address is required")
    @Schema(description = "Address", example = "Avenida Paulista")
    private String address;

    @NotNull(message = "address number is required")
    @Schema(description = "Address number", example = "207")
    @Min(value = 1, message = "number must be an integer greater than zero")
    private Integer number;

    @NotBlank(message = "address complement is required")
    @Schema(description = "Address complement", example = "Apartment 104")
    private String complement;

    @NotBlank(message = "neighborhood is required")
    @Schema(description = "Neighborhood", example = "Bela Vista")
    private String neighborhood;

    @NotBlank(message = "city is required")
    @Schema(description = "City", example = "São Paulo")
    private String city;

    @NotBlank(message = "state is required")
    @Schema(description = "State", example = "São Paulo")
    private String state;

    @NotBlank(message = "zip code is required")
    @Pattern(regexp = "^\\d{5}\\-\\d{3}$", message = "zip code invalid, correct format is XXXXX-XXX")
    @Schema(description = "Zip code in Brazilian standard", example = "01310-100")
    private String zipCode;
}