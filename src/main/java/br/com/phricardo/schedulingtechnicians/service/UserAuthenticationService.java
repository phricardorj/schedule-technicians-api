package br.com.phricardo.schedulingtechnicians.service;

import br.com.phricardo.schedulingtechnicians.dto.request.UserAuthLoginRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.request.UserAuthRegisterRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.request.mapper.UserAuthRegisterRequestMapper;
import br.com.phricardo.schedulingtechnicians.dto.response.TokenResponseDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.mapper.TokenResponseMapper;
import br.com.phricardo.schedulingtechnicians.exception.LoginException;
import br.com.phricardo.schedulingtechnicians.model.User;
import br.com.phricardo.schedulingtechnicians.repository.UserAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Optional.*;
import static java.util.Optional.of;

@Service
@RequiredArgsConstructor
public class UserAuthenticationService implements UserDetailsService {

    private final UserAuthRepository repository;
    private final UserAuthRegisterRequestMapper registerRequestMapper;
    private final TokenResponseMapper tokenResponseMapper;
    private final TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return repository.findByLogin(login);
    }

    public User registerUser(UserAuthRegisterRequestDTO registerRequestDTO) {
        return of(registerRequestDTO)
                .map(registerRequestMapper::from)
                .map(repository::save)
                .orElseThrow(NullPointerException::new);
    }

    public TokenResponseDTO loginUser(UserAuthLoginRequestDTO loginRequestDTO, AuthenticationManager manager) {
        final var login = loginRequestDTO.getLogin();
        final var password = loginRequestDTO.getPassword();
        final var existsByLogin = repository.existsByLogin(login);

        return of(existsByLogin)
                .filter(existsUser -> existsUser)
                .map(existsUser -> new UsernamePasswordAuthenticationToken(login, password))
                .map(token -> ofNullable(manager.authenticate(token))
                        .map(auth -> {
                            final var principal = auth.getPrincipal();
                            return  (User) principal;
                        })
                        .map(tokenService::generate)
                        .map(tokenResponseMapper::from)
                        .orElseThrow(LoginException::new))
                .orElseThrow(LoginException::new);
    }

    public UserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetails) authentication.getPrincipal();
    }

    public User getCurrentUser() {
        UserDetails userDetails = getCurrentUserDetails();
        return (User) userDetails;
    }
}

