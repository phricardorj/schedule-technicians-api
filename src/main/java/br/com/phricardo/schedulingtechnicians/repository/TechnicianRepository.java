package br.com.phricardo.schedulingtechnicians.repository;

import br.com.phricardo.schedulingtechnicians.entities.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Long> {
    Optional<Technician> findByEnrollment(Long enrollment);
}
