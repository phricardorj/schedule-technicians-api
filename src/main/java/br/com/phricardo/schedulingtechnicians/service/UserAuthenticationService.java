package br.com.phricardo.schedulingtechnicians.service;

import br.com.phricardo.schedulingtechnicians.dto.request.UserAuthLoginRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.request.UserAuthRegisterRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.request.mapper.UserAuthRegisterRequestMapper;
import br.com.phricardo.schedulingtechnicians.dto.response.TokenResponseDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.mapper.TokenResponseMapper;
import br.com.phricardo.schedulingtechnicians.model.User;
import br.com.phricardo.schedulingtechnicians.repository.UserAuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Optional.of;

@Service
@AllArgsConstructor
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
        return of(loginRequestDTO)
                .map(r -> new UsernamePasswordAuthenticationToken(r.getLogin(), r.getPassword()))
                .map(manager::authenticate)
                .map(Authentication::getPrincipal)
                .map(principal -> (User) principal)
                .map(tokenService::generate)
                .map(tokenResponseMapper::from)
                .orElseThrow(() -> new RuntimeException("Failed to generate authentication token"));
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

