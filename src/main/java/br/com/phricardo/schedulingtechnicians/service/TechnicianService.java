package br.com.phricardo.schedulingtechnicians.service;

import br.com.phricardo.schedulingtechnicians.dto.request.TechnicianRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.request.mapper.TechnicianRequestMapper;
import br.com.phricardo.schedulingtechnicians.dto.response.TechnicianResponseDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.mapper.TechnicianResponseMapper;
import br.com.phricardo.schedulingtechnicians.dto.update.TechnicianUpdateDTO;
import br.com.phricardo.schedulingtechnicians.dto.update.mapper.TechnicianUpdateMapper;
import br.com.phricardo.schedulingtechnicians.entities.Technician;
import br.com.phricardo.schedulingtechnicians.exception.RegistrationException;
import br.com.phricardo.schedulingtechnicians.repository.TechnicianRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.*;

@Service
public class TechnicianService {

    private final TechnicianRepository repository;
    private final TechnicianRequestMapper requestMapper;
    private final TechnicianResponseMapper responseMapper;
    private final TechnicianUpdateMapper updateMapper;
    private final LocationService locationService;

    public TechnicianService(TechnicianRepository repository, TechnicianRequestMapper requestMapper, TechnicianResponseMapper responseMapper, TechnicianUpdateMapper updateMapper, LocationService locationService) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.updateMapper = updateMapper;
        this.locationService = locationService;
    }

    @Transactional
    public ResponseEntity<TechnicianResponseDTO> register(TechnicianRequestDTO dto) throws RegistrationException {
        Technician technician = requestMapper.from(dto);
        Technician savedTechnician = repository.save(technician);

        if(savedTechnician.getId() == null)
            throw new RegistrationException("Unable to register the company. An internal error occurred in the API.");

        TechnicianResponseDTO technicianResponseDTO = responseMapper.from(savedTechnician);
        String location = locationService.buildLocation("technician/" + technician.getEnrollment());

        return ResponseEntity
                .status(CREATED)
                .header("Location", location)
                .body(technicianResponseDTO);
    }

    public ResponseEntity<TechnicianResponseDTO> getTechnicianByEnrollment(Long enrollment) {
        Technician technician = repository.findByEnrollment(enrollment).orElse(null);
        if(nonNull(technician)) {
            TechnicianResponseDTO technicianResponseDTO = responseMapper.from(technician);
            return ResponseEntity
                    .status(OK)
                    .body(technicianResponseDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<Void> deleteTechnicianByEnrollment(Long enrollment) {
        Technician technician = repository.findByEnrollment(enrollment).orElse(null);
        if(nonNull(technician)) {
            repository.delete(technician);
            return ResponseEntity.noContent().build();
        }
       return ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<Void> update(Long enrollment, TechnicianUpdateDTO technicianUpdateDTO) {
        Technician technician = repository.findByEnrollment(enrollment).orElse(null);
        if(nonNull(technician)) {
            updateMapper.updateTechnicianFromDTO(technicianUpdateDTO, technician);
            repository.save(technician);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
