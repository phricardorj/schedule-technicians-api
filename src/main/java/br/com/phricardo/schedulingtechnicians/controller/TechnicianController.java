package br.com.phricardo.schedulingtechnicians.controller;

import br.com.phricardo.schedulingtechnicians.dto.request.TechnicianRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.TechnicianResponseDTO;
import br.com.phricardo.schedulingtechnicians.dto.update.CustomerUpdateDTO;
import br.com.phricardo.schedulingtechnicians.dto.update.TechnicianUpdateDTO;
import br.com.phricardo.schedulingtechnicians.entities.Technician;
import br.com.phricardo.schedulingtechnicians.service.TechnicianService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/technician")
@Tag(name = "Technician", description = "Endpoints to manage technician information")
public class TechnicianController {

    private final TechnicianService technicianService;

    public TechnicianController(TechnicianService technicianService) {
        this.technicianService = technicianService;
    }

    @PostMapping
    public ResponseEntity<TechnicianResponseDTO> register(@Valid @RequestBody TechnicianRequestDTO technicianRequestDTO) {
       return technicianService.register(technicianRequestDTO);
    }

    @GetMapping("/{enrollment}")
    public ResponseEntity<TechnicianResponseDTO> getTechnicianByEnrollment(@PathVariable Long enrollment) {
        return technicianService.getTechnicianByEnrollment(enrollment);
    }

    @PatchMapping("/{enrollment}")
    public ResponseEntity<Void> update(@PathVariable Long enrollment, @Valid @RequestBody TechnicianUpdateDTO technicianUpdateDTO) {
        return technicianService.update(enrollment, technicianUpdateDTO);
    }

    @DeleteMapping("/{enrollment}")
    public ResponseEntity<Void> delete(@PathVariable Long enrollment) {
        return technicianService.deleteTechnicianByEnrollment(enrollment);
    }
}
