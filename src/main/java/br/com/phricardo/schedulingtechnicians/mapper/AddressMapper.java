package br.com.phricardo.schedulingtechnicians.mapper;

import br.com.phricardo.schedulingtechnicians.dto.AddressDTO;
import br.com.phricardo.schedulingtechnicians.entities.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address from(AddressDTO addressDTO);
}

