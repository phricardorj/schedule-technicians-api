package br.com.phricardo.schedulingtechnicians.service;

import br.com.phricardo.schedulingtechnicians.dto.request.SchedulingRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.request.mapper.SchedulingRequestMapper;
import br.com.phricardo.schedulingtechnicians.dto.response.SchedulingResponseDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.mapper.SchedulingResponseMapper;
import br.com.phricardo.schedulingtechnicians.entities.Scheduling;
import br.com.phricardo.schedulingtechnicians.repository.SchedulingRepository;
import org.springframework.stereotype.Service;

@Service
public class SchedulingService {

    private final SchedulingRepository repository;
    private final SchedulingRequestMapper requestMapper;
    private final SchedulingResponseMapper responseMapper;

    public SchedulingService(SchedulingRepository repository, SchedulingRequestMapper requestMapper, SchedulingResponseMapper responseMapper) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    public SchedulingResponseDTO register(SchedulingRequestDTO dto) {
        Scheduling scheduling = requestMapper.from(dto);
        scheduling = repository.save(scheduling);
        return responseMapper.from(scheduling);
    }

    public Scheduling getSchedulingByServiceOrder(String os) {
        return repository.findByOs(os);
    }
}
