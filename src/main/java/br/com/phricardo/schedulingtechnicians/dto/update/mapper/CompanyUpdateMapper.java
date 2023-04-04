package br.com.phricardo.schedulingtechnicians.dto.update.mapper;

import br.com.phricardo.schedulingtechnicians.dto.update.CompanyUpdateDTO;
import br.com.phricardo.schedulingtechnicians.entities.Company;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = AddressUpdateMapper.class)
public interface CompanyUpdateMapper {

    void updateCompanyFromDTO(CompanyUpdateDTO companyUpdateDTO, @MappingTarget Company company);
}
