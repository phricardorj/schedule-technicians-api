package br.com.phricardo.schedulingtechnicians.dto.update.mapper;

import br.com.phricardo.schedulingtechnicians.dto.update.AddressUpdateDTO;
import br.com.phricardo.schedulingtechnicians.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressUpdateMapper {
    @Mapping(source = "number", target = "addressNumber")
    Address from(AddressUpdateDTO addressUpdateDTO);
}

