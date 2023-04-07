package br.com.phricardo.schedulingtechnicians.dto.response.mapper;

import br.com.phricardo.schedulingtechnicians.dto.response.CompanyResponseDTO;
import br.com.phricardo.schedulingtechnicians.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AddressResponseMapper.class)
public interface CompanyResponseMapper {
    @Mapping(source = "id", target = "companyId")
    @Mapping(source = "address", target = "address")
    CompanyResponseDTO from(Company company);
}
