package br.com.phricardo.schedulingtechnicians.model;

import br.com.phricardo.schedulingtechnicians.util.ServiceOrderUtil;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Scheduling")
@Table(name = "schedules")
public class Scheduling {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String os;
    private LocalDateTime date;
    private Long customerId;

    @PrePersist
    private void prePersist() {
        this.os = ServiceOrderUtil.generate();
    }
}
