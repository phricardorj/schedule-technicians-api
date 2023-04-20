package br.com.phricardo.schedulingtechnicians.service;

import br.com.phricardo.schedulingtechnicians.model.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
@AllArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager;

    public UserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetails) authentication.getPrincipal();
    }

    public User getCurrentUser() {
        UserDetails userDetails = getCurrentUserDetails();
        return (User) userDetails;
    }
}
