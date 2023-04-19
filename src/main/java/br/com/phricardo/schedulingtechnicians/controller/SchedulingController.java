package br.com.phricardo.schedulingtechnicians.controller;

import br.com.phricardo.schedulingtechnicians.dto.request.SchedulingRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.SchedulingResponseDTO;
import br.com.phricardo.schedulingtechnicians.dto.update.SchedulingUpdateDTO;
import br.com.phricardo.schedulingtechnicians.service.SchedulingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/scheduling")
@Tag(name = "Scheduling", description = "Endpoints for managing scheduling information")
public class SchedulingController {

    private final SchedulingService schedulingService;

    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    @PostMapping
    public ResponseEntity<?> schedule(@Valid @RequestBody SchedulingRequestDTO schedulingRequestDTO) {
       return schedulingService.register(schedulingRequestDTO);
    }

    @GetMapping("/{os}")
    public ResponseEntity<SchedulingResponseDTO> getSchedulingByServiceOrder(@PathVariable String os) {
        return schedulingService.getSchedulingByServiceOrder(os);
    }

    @PutMapping("/{os}")
    public ResponseEntity<SchedulingResponseDTO> update(@PathVariable String os, @Valid @RequestBody SchedulingUpdateDTO schedulingUpdateDTO) {
        return schedulingService.update(os, schedulingUpdateDTO);
    }

    @DeleteMapping("/{os}")
    public void delete(@PathVariable String os) {
        schedulingService.deleteSchedulingByServiceOrder(os);
    }
}
