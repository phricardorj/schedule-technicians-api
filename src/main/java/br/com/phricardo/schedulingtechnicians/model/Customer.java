package br.com.phricardo.schedulingtechnicians.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Customer")
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    private LocalDate dateOfBirth;
    private String email;
    private String phone;
    private String cellphone;

    @Embedded
    private Address address;
}
