package br.com.phricardo.schedulingtechnicians.dto.response.mapper;

import br.com.phricardo.schedulingtechnicians.dto.response.TechnicianResponseDTO;
import br.com.phricardo.schedulingtechnicians.entities.Technician;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TechnicianResponseMapper {
    @Mapping(source = "address", target = "address")
    TechnicianResponseDTO from(Technician technician);
}
