package br.com.phricardo.schedulingtechnicians.service;

import br.com.phricardo.schedulingtechnicians.dto.request.SchedulingRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.request.mapper.SchedulingRequestMapper;
import br.com.phricardo.schedulingtechnicians.dto.response.SchedulingResponseDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.mapper.SchedulingResponseMapper;
import br.com.phricardo.schedulingtechnicians.dto.update.SchedulingUpdateDTO;
import br.com.phricardo.schedulingtechnicians.dto.update.mapper.SchedulingUpdateMapper;
import br.com.phricardo.schedulingtechnicians.entities.Customer;
import br.com.phricardo.schedulingtechnicians.entities.Scheduling;
import br.com.phricardo.schedulingtechnicians.exception.RegistrationException;
import br.com.phricardo.schedulingtechnicians.repository.CustomerRepository;
import br.com.phricardo.schedulingtechnicians.repository.SchedulingRepository;
import br.com.phricardo.schedulingtechnicians.util.LocationUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.*;

@Service
public class SchedulingService {

    private final SchedulingRepository repository;
    private final SchedulingRequestMapper requestMapper;
    private final SchedulingResponseMapper responseMapper;
    private final SchedulingUpdateMapper updateMapper;
    private final CustomerRepository customerRepository;
    private final LocationUtil locationUtil;

    public SchedulingService(SchedulingRepository repository, SchedulingRequestMapper requestMapper, SchedulingResponseMapper responseMapper, SchedulingUpdateMapper updateMapper, CustomerRepository customerRepository, LocationUtil locationUtil) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.updateMapper = updateMapper;
        this.customerRepository = customerRepository;
        this.locationUtil = locationUtil;
    }

    public ResponseEntity<?> register(SchedulingRequestDTO dto) throws RegistrationException {
        Customer customer = customerRepository.findById(dto.getCustomerId()).orElse(null);
        if(nonNull(customer)) {
            Scheduling scheduling = requestMapper.from(dto);
            Scheduling savedScheduling = repository.save(scheduling);

            if(savedScheduling.getId() == null)
                throw new RegistrationException("Unable to register the scheduling. An internal error occurred in the API.");

            SchedulingResponseDTO schedulingResponseDTO = responseMapper.from(savedScheduling);
            String location = locationUtil.buildLocation("scheduling/" + scheduling.getOs());

            return ResponseEntity
                    .status(CREATED)
                    .header("Location", location)
                    .body(schedulingResponseDTO);
        }
        return ResponseEntity.status(NOT_FOUND).body("customer id not found");
    }

    public ResponseEntity<SchedulingResponseDTO> getSchedulingByServiceOrder(String os) {
        Scheduling scheduling = repository.findByOs(os);
        if(nonNull(scheduling)) {
            SchedulingResponseDTO schedulingResponseDTO = responseMapper.from(scheduling);
            return ResponseEntity
                    .status(OK)
                    .body(schedulingResponseDTO);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> deleteSchedulingByServiceOrder(String os) {
        Scheduling scheduling = repository.findByOs(os);
        if(nonNull(scheduling)) {
            repository.delete(scheduling);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> update(String os, SchedulingUpdateDTO schedulingUpdateDTO) {
        Scheduling scheduling = repository.findByOs(os);
        if(nonNull(scheduling)) {
            updateMapper.updateSchedulingFromDTO(schedulingUpdateDTO, scheduling);
            repository.save(scheduling);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
