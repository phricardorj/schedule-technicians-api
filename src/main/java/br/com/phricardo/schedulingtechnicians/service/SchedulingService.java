package br.com.phricardo.schedulingtechnicians.service;

import br.com.phricardo.schedulingtechnicians.dto.request.SchedulingRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.request.mapper.SchedulingRequestMapper;
import br.com.phricardo.schedulingtechnicians.dto.response.SchedulingResponseDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.mapper.SchedulingResponseMapper;
import br.com.phricardo.schedulingtechnicians.entities.Scheduling;
import br.com.phricardo.schedulingtechnicians.repository.SchedulingRepository;
import br.com.phricardo.schedulingtechnicians.util.LocationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.*;

@Service
public class SchedulingService {

    private final SchedulingRepository repository;
    private final SchedulingRequestMapper requestMapper;
    private final SchedulingResponseMapper responseMapper;
    private final LocationUtil locationUtil;

    public SchedulingService(SchedulingRepository repository, SchedulingRequestMapper requestMapper, SchedulingResponseMapper responseMapper, LocationUtil locationUtil) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.locationUtil = locationUtil;
    }

    public ResponseEntity<SchedulingResponseDTO> register(SchedulingRequestDTO dto) {
        Scheduling scheduling = requestMapper.from(dto);
        scheduling = repository.save(scheduling);
        SchedulingResponseDTO schedulingResponseDTO = responseMapper.from(scheduling);
        String location = locationUtil.buildLocation("scheduling/" + scheduling.getOs());

        return ResponseEntity
                .status(CREATED)
                .header("Location", location)
                .body(schedulingResponseDTO);
    }

    public ResponseEntity<SchedulingResponseDTO> getSchedulingByServiceOrder(String os) {
        Scheduling scheduling = repository.findByOs(os);
        SchedulingResponseDTO schedulingResponseDTO = responseMapper.from(scheduling);

        return ResponseEntity
                .status(OK)
                .body(schedulingResponseDTO);
    }

    public ResponseEntity<Void> deleteSchedulingByServiceOrder(String os) {
        Scheduling scheduling = repository.findByOs(os);
        if(nonNull(scheduling)) {
            repository.delete(scheduling);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
