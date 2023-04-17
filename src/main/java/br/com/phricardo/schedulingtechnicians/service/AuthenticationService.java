package br.com.phricardo.schedulingtechnicians.service;

import br.com.phricardo.schedulingtechnicians.dto.request.AuthLoginRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.request.AuthRegisterRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.request.mapper.AuthRegisterRequestMapper;
import br.com.phricardo.schedulingtechnicians.dto.response.TokenResponseDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.mapper.TokenResponseMapper;
import br.com.phricardo.schedulingtechnicians.model.user.User;
import br.com.phricardo.schedulingtechnicians.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Optional.of;

@Service
@AllArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final UserRepository repository;
    private final AuthRegisterRequestMapper registerRequestMapper;
    private final TokenResponseMapper tokenResponseMapper;
    private final TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return repository.findByLogin(login);
    }

    public User registerUser(AuthRegisterRequestDTO registerRequestDTO) {
        return of(registerRequestDTO)
                .map(registerRequestMapper::from)
                .map(repository::save)
                .orElseThrow(NullPointerException::new);
    }

    public TokenResponseDTO loginUser(AuthLoginRequestDTO loginRequestDTO, AuthenticationManager manager) {
        return of(loginRequestDTO)
                .map(r -> new UsernamePasswordAuthenticationToken(r.getLogin(), r.getPassword()))
                .map(manager::authenticate)
                .map(Authentication::getPrincipal)
                .map(principal -> (User) principal)
                .map(tokenService::generate)
                .map(tokenResponseMapper::from)
                .orElseThrow(() -> new RuntimeException("Failed to generate authentication token"));
   }
}

