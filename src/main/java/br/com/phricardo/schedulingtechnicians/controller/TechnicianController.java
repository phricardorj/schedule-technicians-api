package br.com.phricardo.schedulingtechnicians.controller;

import br.com.phricardo.schedulingtechnicians.dto.request.TechnicianRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.TechnicianResponseDTO;
import br.com.phricardo.schedulingtechnicians.entities.Technician;
import br.com.phricardo.schedulingtechnicians.service.TechnicianService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/technician")
public class TechnicianController {

    private final TechnicianService technicianService;

    public TechnicianController(TechnicianService technicianService) {
        this.technicianService = technicianService;
    }

    @PostMapping
    public TechnicianResponseDTO register(@Valid @RequestBody TechnicianRequestDTO technicianRequestDTO) {
       return technicianService.register(technicianRequestDTO);
    }

    @GetMapping("/{enrollment}")
    public Technician getTechnicianByEnrollment(@PathVariable Long enrollment) {
        return technicianService.getTechnicianByEnrollment(enrollment);
    }
}
