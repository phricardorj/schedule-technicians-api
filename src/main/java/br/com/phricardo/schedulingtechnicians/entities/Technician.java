package br.com.phricardo.schedulingtechnicians.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "technician")
@Table(name = "technicians")
public class Technician {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    private LocalDate dateOfBirth;
    private String phone;
    private String cellphone;

    @Embedded
    private Address address;
}
