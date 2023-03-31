package br.com.phricardo.schedulingtechnicians.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDTO {
    @NotBlank
    private String street;
    @NotBlank
    private String neighborhood;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
    @NotBlank @Pattern(regexp = "^\\d{5}\\-\\d{3}$")
    private String zipCode;
}
