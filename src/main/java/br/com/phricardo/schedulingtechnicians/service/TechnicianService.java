package br.com.phricardo.schedulingtechnicians.service;

import br.com.phricardo.schedulingtechnicians.dto.request.TechnicianRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.request.mapper.TechnicianRequestMapper;
import br.com.phricardo.schedulingtechnicians.dto.response.TechnicianResponseDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.mapper.TechnicianResponseMapper;
import br.com.phricardo.schedulingtechnicians.entities.Technician;
import br.com.phricardo.schedulingtechnicians.repository.TechnicianRepository;
import org.springframework.stereotype.Service;

@Service
public class TechnicianService {

    private final TechnicianRepository repository;
    private final TechnicianRequestMapper requestMapper;
    private final TechnicianResponseMapper responseMapper;

    public TechnicianService(TechnicianRepository repository, TechnicianRequestMapper requestMapper, TechnicianResponseMapper responseMapper) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    public TechnicianResponseDTO register(TechnicianRequestDTO dto) {
        Technician technician = requestMapper.from(dto);
        technician = repository.save(technician);
        return responseMapper.from(technician);
    }

    public Technician getTechnicianByEnrollment(Long enrollment) {
        return repository.findByEnrollment(enrollment);
    }
}
