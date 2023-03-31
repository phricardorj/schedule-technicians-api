package br.com.phricardo.schedulingtechnicians.controller;

import br.com.phricardo.schedulingtechnicians.dto.request.SchedulingRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.SchedulingResponseDTO;
import br.com.phricardo.schedulingtechnicians.entities.Scheduling;
import br.com.phricardo.schedulingtechnicians.service.SchedulingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/scheduling")
public class SchedulingController {

    private final SchedulingService schedulingService;

    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    @PostMapping
    public SchedulingResponseDTO schedule(@Valid @RequestBody SchedulingRequestDTO schedulingRequestDTO) {
       return schedulingService.register(schedulingRequestDTO);
    }

    @GetMapping("/{os}")
    public Scheduling getSchedulingByServiceOrder(@PathVariable String os) {
        return schedulingService.getSchedulingByServiceOrder(os);
    }
}
