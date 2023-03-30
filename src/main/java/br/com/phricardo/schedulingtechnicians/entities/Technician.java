package br.com.phricardo.schedulingtechnicians.entities;

import br.com.phricardo.schedulingtechnicians.util.EnrollmentGenerator;
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
    private String email;
    private String phone;
    private String cellphone;
    private Integer enrollment;

    @Embedded
    private Address address;

    @PrePersist
    private void prePersist() {
        this.enrollment = EnrollmentGenerator.generate();
    }
}
