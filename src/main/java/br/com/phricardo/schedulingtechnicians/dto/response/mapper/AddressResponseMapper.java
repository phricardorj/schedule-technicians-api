package br.com.phricardo.schedulingtechnicians.dto.response.mapper;

import br.com.phricardo.schedulingtechnicians.dto.response.AddressResponseDTO;
import br.com.phricardo.schedulingtechnicians.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressResponseMapper {
    @Mapping(source = "addressNumber", target = "number")
    AddressResponseDTO from(Address address);
}

