package br.com.phricardo.schedulingtechnicians.dto.response.mapper;

import br.com.phricardo.schedulingtechnicians.dto.response.CustomerResponseDTO;
import br.com.phricardo.schedulingtechnicians.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AddressResponseMapper.class)
public interface CustomerResponseMapper {
    @Mapping(source = "id", target = "customerId")
    @Mapping(source = "address", target = "address")
    CustomerResponseDTO from(Customer customer);
}
