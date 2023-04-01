package br.com.phricardo.schedulingtechnicians.entities;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "company")
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String cnpj;
    private String email;
    private String phone;
    private String cellphone;

    @Embedded
    private Address address;
}
