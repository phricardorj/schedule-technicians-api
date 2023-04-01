package br.com.phricardo.schedulingtechnicians.dto.request.mapper;

import br.com.phricardo.schedulingtechnicians.dto.request.CompanyRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.request.CustomerRequestDTO;
import br.com.phricardo.schedulingtechnicians.entities.Company;
import br.com.phricardo.schedulingtechnicians.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AddressRequestMapper.class)
public interface CompanyRequestMapper {
    @Mapping(source = "address", target = "address")
    Company from(CompanyRequestDTO companyRequestDTO);
}
