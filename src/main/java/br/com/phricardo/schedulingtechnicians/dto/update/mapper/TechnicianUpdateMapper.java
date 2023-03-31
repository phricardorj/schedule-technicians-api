package br.com.phricardo.schedulingtechnicians.dto.update.mapper;

import br.com.phricardo.schedulingtechnicians.dto.update.TechnicianUpdateDTO;
import br.com.phricardo.schedulingtechnicians.entities.Technician;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TechnicianUpdateMapper {

    void updateTechnicianFromDTO(TechnicianUpdateDTO technicianUpdateDTO, @MappingTarget Technician technician);
}
