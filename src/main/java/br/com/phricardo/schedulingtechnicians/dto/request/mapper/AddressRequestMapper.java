package br.com.phricardo.schedulingtechnicians.dto.request.mapper;

import br.com.phricardo.schedulingtechnicians.dto.request.AddressRequestDTO;
import br.com.phricardo.schedulingtechnicians.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressRequestMapper {
    @Mapping(source = "number", target = "addressNumber")
    Address from(AddressRequestDTO addressRequestDTO);
}


