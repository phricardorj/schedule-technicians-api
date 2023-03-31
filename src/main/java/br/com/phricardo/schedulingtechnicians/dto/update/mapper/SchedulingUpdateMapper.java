package br.com.phricardo.schedulingtechnicians.dto.update.mapper;

import br.com.phricardo.schedulingtechnicians.dto.update.SchedulingUpdateDTO;
import br.com.phricardo.schedulingtechnicians.entities.Scheduling;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SchedulingUpdateMapper {

    void updateSchedulingFromDTO(SchedulingUpdateDTO schedulingUpdateDTO, @MappingTarget Scheduling scheduling);
}
