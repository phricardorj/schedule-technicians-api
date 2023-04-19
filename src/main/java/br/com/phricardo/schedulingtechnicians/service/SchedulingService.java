package br.com.phricardo.schedulingtechnicians.service;

import br.com.phricardo.schedulingtechnicians.dto.request.SchedulingRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.request.mapper.SchedulingRequestMapper;
import br.com.phricardo.schedulingtechnicians.dto.response.SchedulingResponseDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.mapper.SchedulingResponseMapper;
import br.com.phricardo.schedulingtechnicians.dto.update.SchedulingUpdateDTO;
import br.com.phricardo.schedulingtechnicians.dto.update.mapper.SchedulingUpdateMapper;
import br.com.phricardo.schedulingtechnicians.exception.RegistrationException;
import br.com.phricardo.schedulingtechnicians.model.Customer;
import br.com.phricardo.schedulingtechnicians.repository.CustomerRepository;
import br.com.phricardo.schedulingtechnicians.repository.SchedulingRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

import static java.util.Optional.*;

@Service
@AllArgsConstructor
public class SchedulingService {

    private final SchedulingRepository repository;
    private final SchedulingRequestMapper requestMapper;
    private final SchedulingResponseMapper responseMapper;
    private final SchedulingUpdateMapper updateMapper;
    private final CustomerRepository customerRepository;
    private final LocationService locationService;

    @Transactional
    public ResponseEntity<?> register(SchedulingRequestDTO schedulingRequestDTO) throws RegistrationException {
        Customer customer = customerRepository.findById(schedulingRequestDTO.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + schedulingRequestDTO.getCustomerId()));

        return of(schedulingRequestDTO)
                .map(requestMapper::from)
                .map(repository::save)
                .map(saved ->
                    ResponseEntity.created(
                    URI.create(locationService.buildLocation("scheduling/" + saved.getOs())))
                    .body(responseMapper.from(saved))
                ).orElseThrow(() -> new RegistrationException("Failed to register. Please verify the provided data and try again."));
    }

    public ResponseEntity<SchedulingResponseDTO> getSchedulingByServiceOrder(String serviceOrder) {
        return repository.findByOs(serviceOrder)
                .map(responseMapper::from)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Scheduling not found with service order: " + serviceOrder));
    }

    @Transactional
    public void deleteSchedulingByServiceOrder(String serviceOrder) {
        repository.delete(repository.findByOs(serviceOrder)
                .orElseThrow(() -> new EntityNotFoundException("Scheduling not found with service order: " + serviceOrder)));
    }

    @Transactional
    public ResponseEntity<SchedulingResponseDTO> update(String serviceOrder, SchedulingUpdateDTO schedulingUpdateDTO) {
        return repository.findByOs(serviceOrder)
                .map(scheduling -> {
                    updateMapper.updateSchedulingFromDTO(schedulingUpdateDTO, scheduling);
                    return repository.save(scheduling);
                })
                .map(updated ->
                        ResponseEntity.created(
                                        URI.create(locationService.buildLocation("scheduling/" + updated.getOs())))
                                .body(responseMapper.from(updated)))
                .orElseThrow(() -> new EntityNotFoundException("Scheduling not found with service order: " + serviceOrder));
    }
}
