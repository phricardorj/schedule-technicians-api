package br.com.phricardo.schedulingtechnicians.service;

import br.com.phricardo.schedulingtechnicians.dto.request.TechnicianRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.request.mapper.TechnicianRequestMapper;
import br.com.phricardo.schedulingtechnicians.dto.response.TechnicianResponseDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.mapper.TechnicianResponseMapper;
import br.com.phricardo.schedulingtechnicians.dto.update.TechnicianUpdateDTO;
import br.com.phricardo.schedulingtechnicians.dto.update.mapper.TechnicianUpdateMapper;
import br.com.phricardo.schedulingtechnicians.exception.RegistrationException;
import br.com.phricardo.schedulingtechnicians.repository.TechnicianRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

import static java.util.Optional.*;

@Service
@RequiredArgsConstructor
public class TechnicianService {

    private final TechnicianRepository repository;
    private final TechnicianRequestMapper requestMapper;
    private final TechnicianResponseMapper responseMapper;
    private final TechnicianUpdateMapper updateMapper;
    private final LocationService locationService;

    @Transactional
    public ResponseEntity<TechnicianResponseDTO> register(TechnicianRequestDTO dto) {
        return of(requestMapper.from(dto))
                .map(repository::save)
                .map(saved ->
                        ResponseEntity.created(
                                        URI.create(locationService.buildLocation("technician/" + saved.getEnrollment())))
                                .body(responseMapper.from(saved))
                )
                .orElseThrow(() -> new RegistrationException("Failed to register. Please verify the provided data and try again."));
    }

    public ResponseEntity<TechnicianResponseDTO> getTechnicianByEnrollment(Long enrollment) {
        return repository.findByEnrollment(enrollment)
                .map(responseMapper::from)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Technician not found with enrollment: " + enrollment));
    }

    @Transactional
    public void deleteTechnicianByEnrollment(Long enrollment) {
        repository.delete(repository.findByEnrollment(enrollment)
                .orElseThrow(() -> new EntityNotFoundException("Technician not found with enrollment: " + enrollment)));
    }

    @Transactional
    public ResponseEntity<TechnicianResponseDTO> update(Long enrollment, TechnicianUpdateDTO technicianUpdateDTO) {
        return repository.findById(enrollment)
                .map(technician -> {
                    updateMapper.updateTechnicianFromDTO(technicianUpdateDTO, technician);
                    return repository.save(technician);
                })
                .map(updated ->
                        ResponseEntity.created(
                                        URI.create(locationService.buildLocation("technician/" + updated.getEnrollment())))
                                .body(responseMapper.from(updated)))
                .orElseThrow(() -> new EntityNotFoundException("Technician not found with enrollment: " + enrollment));
    }
}
