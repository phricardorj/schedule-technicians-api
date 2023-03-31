package br.com.phricardo.schedulingtechnicians.dto.request.mapper;

import br.com.phricardo.schedulingtechnicians.dto.request.AddressRequestDTO;
import br.com.phricardo.schedulingtechnicians.entities.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressRequestMapper {

    Address from(AddressRequestDTO addressRequestDTO);
}

