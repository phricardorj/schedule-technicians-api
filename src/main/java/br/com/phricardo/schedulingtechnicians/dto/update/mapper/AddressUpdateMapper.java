package br.com.phricardo.schedulingtechnicians.dto.update.mapper;

import br.com.phricardo.schedulingtechnicians.dto.update.AddressUpdateDTO;
import br.com.phricardo.schedulingtechnicians.entities.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressUpdateMapper {

    Address from(AddressUpdateDTO addressUpdateDTO);
}

