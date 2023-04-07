package br.com.phricardo.schedulingtechnicians.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String address;
    private Integer addressNumber;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;
}
