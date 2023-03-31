package br.com.phricardo.schedulingtechnicians.dto.request.mapper;

import br.com.phricardo.schedulingtechnicians.dto.request.SchedulingRequestDTO;
import br.com.phricardo.schedulingtechnicians.entities.Scheduling;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchedulingRequestMapper {

    Scheduling from(SchedulingRequestDTO schedulingRequestDTO);
}
