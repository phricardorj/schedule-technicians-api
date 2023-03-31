package br.com.phricardo.schedulingtechnicians.service;

import br.com.phricardo.schedulingtechnicians.dto.SchedulingDTO;
import br.com.phricardo.schedulingtechnicians.entities.Scheduling;
import br.com.phricardo.schedulingtechnicians.mapper.SchedulingMapper;
import br.com.phricardo.schedulingtechnicians.repository.SchedulingRepository;
import org.springframework.stereotype.Service;

@Service
public class SchedulingService {

    private final SchedulingRepository repository;
    private final SchedulingMapper mapper;

    public SchedulingService(SchedulingRepository repository, SchedulingMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void register(SchedulingDTO dto) {
        Scheduling scheduling = mapper.from(dto);
        repository.save(scheduling);
    }

    public Scheduling getSchedulingByServiceOrder(String os) {
        return repository.findByOs(os);
    }
}
