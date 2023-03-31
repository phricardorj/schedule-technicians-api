package br.com.phricardo.schedulingtechnicians.mapper;

import br.com.phricardo.schedulingtechnicians.dto.CustomerDTO;
import br.com.phricardo.schedulingtechnicians.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(source = "address", target = "address")
    Customer from(CustomerDTO customerDTO);
}
