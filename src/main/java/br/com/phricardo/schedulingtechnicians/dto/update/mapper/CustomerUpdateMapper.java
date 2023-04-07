package br.com.phricardo.schedulingtechnicians.dto.update.mapper;

import br.com.phricardo.schedulingtechnicians.dto.update.CustomerUpdateDTO;
import br.com.phricardo.schedulingtechnicians.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = AddressUpdateMapper.class)
public interface CustomerUpdateMapper {

    void updateCustomerFromDTO(CustomerUpdateDTO customerUpdateDTO, @MappingTarget Customer customer);
}
