package br.com.phricardo.schedulingtechnicians.mapper;

import br.com.phricardo.schedulingtechnicians.dto.SchedulingDTO;
import br.com.phricardo.schedulingtechnicians.entities.Scheduling;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchedulingMapper {

    Scheduling from(SchedulingDTO schedulingDTO);
}
