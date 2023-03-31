package br.com.phricardo.schedulingtechnicians.service;

import br.com.phricardo.schedulingtechnicians.dto.request.TechnicianRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.request.mapper.TechnicianRequestMapper;
import br.com.phricardo.schedulingtechnicians.dto.response.TechnicianResponseDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.mapper.TechnicianResponseMapper;
import br.com.phricardo.schedulingtechnicians.entities.Technician;
import br.com.phricardo.schedulingtechnicians.repository.TechnicianRepository;
import br.com.phricardo.schedulingtechnicians.util.LocationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TechnicianService {

    private final TechnicianRepository repository;
    private final TechnicianRequestMapper requestMapper;
    private final TechnicianResponseMapper responseMapper;
    private final LocationUtil locationUtil;

    public TechnicianService(TechnicianRepository repository, TechnicianRequestMapper requestMapper, TechnicianResponseMapper responseMapper, LocationUtil locationUtil) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.locationUtil = locationUtil;
    }

    public ResponseEntity<TechnicianResponseDTO> register(TechnicianRequestDTO dto) {
        Technician technician = requestMapper.from(dto);
        technician = repository.save(technician);
        TechnicianResponseDTO technicianResponseDTO = responseMapper.from(technician);
        String location = locationUtil.buildLocation("technician/" + technician.getEnrollment());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", location)
                .body(technicianResponseDTO);
    }

    public Technician getTechnicianByEnrollment(Long enrollment) {
        return repository.findByEnrollment(enrollment);
    }
}
