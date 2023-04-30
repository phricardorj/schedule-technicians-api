package br.com.phricardo.schedulingtechnicians.repository;

import br.com.phricardo.schedulingtechnicians.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);

    UserDetails findByEmailOrCpf(String email, String cpf);

    boolean existsByEmailOrCpf(String email, String cpf);

    boolean existsByEmail(String email);
}
