package br.com.phricardo.schedulingtechnicians.mapper;

import br.com.phricardo.schedulingtechnicians.dto.TechnicianDTO;
import br.com.phricardo.schedulingtechnicians.entities.Technician;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TechnicianMapper {
    @Mapping(source = "address", target = "address")
    Technician from(TechnicianDTO technicianDTO);
}
