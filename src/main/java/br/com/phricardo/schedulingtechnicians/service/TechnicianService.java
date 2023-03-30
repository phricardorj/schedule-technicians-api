package br.com.phricardo.schedulingtechnicians.service;

import br.com.phricardo.schedulingtechnicians.dto.TechnicianDTO;
import br.com.phricardo.schedulingtechnicians.entities.Technician;
import br.com.phricardo.schedulingtechnicians.mapper.TechnicianMapper;
import br.com.phricardo.schedulingtechnicians.repository.TechnicianRepository;
import org.springframework.stereotype.Service;

@Service
public class TechnicianService {

    private final TechnicianRepository repository;
    private final TechnicianMapper mapper;

    public TechnicianService(TechnicianRepository repository, TechnicianMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void register(TechnicianDTO dto) {
        Technician technician = mapper.technicianDTOtoTechnician(dto);
        repository.save(technician);
    }
}
