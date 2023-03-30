package br.com.phricardo.schedulingtechnicians.dto;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
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
