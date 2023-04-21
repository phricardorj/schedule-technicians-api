package br.com.phricardo.schedulingtechnicians.dto.request.mapper;

import br.com.phricardo.schedulingtechnicians.dto.request.UserAuthRegisterRequestDTO;
import br.com.phricardo.schedulingtechnicians.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UserAuthRegisterRequestMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Mapping(source = "password", target = "password", qualifiedByName = "encodePassword")
    public abstract User from(UserAuthRegisterRequestDTO userAuthRegisterRequestDTO);

    @Named("encodePassword")
    protected String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
