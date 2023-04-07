package br.com.phricardo.schedulingtechnicians.dto.update.mapper;

import br.com.phricardo.schedulingtechnicians.dto.update.TechnicianUpdateDTO;
import br.com.phricardo.schedulingtechnicians.model.Technician;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = AddressUpdateMapper.class)
public interface TechnicianUpdateMapper {

    void updateTechnicianFromDTO(TechnicianUpdateDTO technicianUpdateDTO, @MappingTarget Technician technician);
}
