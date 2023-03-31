package br.com.phricardo.schedulingtechnicians.dto.response.mapper;

import br.com.phricardo.schedulingtechnicians.dto.response.AddressResponseDTO;
import br.com.phricardo.schedulingtechnicians.entities.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressResponseMapper {

    AddressResponseDTO from(Address address);
}

