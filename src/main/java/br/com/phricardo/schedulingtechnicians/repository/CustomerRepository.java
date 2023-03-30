package br.com.phricardo.schedulingtechnicians.repository;

import br.com.phricardo.schedulingtechnicians.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
