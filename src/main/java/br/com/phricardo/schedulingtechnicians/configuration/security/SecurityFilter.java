package br.com.phricardo.schedulingtechnicians.configuration.security;

import br.com.phricardo.schedulingtechnicians.repository.UserRepository;
import br.com.phricardo.schedulingtechnicians.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final var tokenJWT = getHeaderToken(request);
        if(tokenJWT != null)  {
            final var subject = tokenService.validateAndGetSubject(tokenJWT);
            final var user = userRepository.findByLogin(subject);
            final var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String getHeaderToken(HttpServletRequest request) {
        final var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null) return authorizationHeader.replace("Bearer ", "");
        return null;
    }
}
