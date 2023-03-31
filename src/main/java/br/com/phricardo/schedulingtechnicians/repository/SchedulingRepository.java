package br.com.phricardo.schedulingtechnicians.repository;

import br.com.phricardo.schedulingtechnicians.entities.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {
}
