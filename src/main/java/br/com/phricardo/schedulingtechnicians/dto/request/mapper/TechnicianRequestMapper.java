package br.com.phricardo.schedulingtechnicians.dto.request.mapper;

import br.com.phricardo.schedulingtechnicians.dto.request.TechnicianRequestDTO;
import br.com.phricardo.schedulingtechnicians.model.Technician;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AddressRequestMapper.class)
public interface TechnicianRequestMapper {
    @Mapping(source = "address", target = "address")
    Technician from(TechnicianRequestDTO technicianRequestDTO);
}
