package br.com.phricardo.schedulingtechnicians.dto.response.mapper;

import br.com.phricardo.schedulingtechnicians.dto.response.SchedulingResponseDTO;
import br.com.phricardo.schedulingtechnicians.entities.Scheduling;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchedulingResponseMapper {

    SchedulingResponseDTO from(Scheduling scheduling);
}
