package br.com.phricardo.schedulingtechnicians.dto.response.mapper;

import br.com.phricardo.schedulingtechnicians.dto.response.TechnicianResponseDTO;
import br.com.phricardo.schedulingtechnicians.model.Technician;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AddressResponseMapper.class)
public interface TechnicianResponseMapper {
    @Mapping(source = "address", target = "address")
    TechnicianResponseDTO from(Technician technician);
}
