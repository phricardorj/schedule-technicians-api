package br.com.phricardo.schedulingtechnicians.controller;

import br.com.phricardo.schedulingtechnicians.dto.request.CompanyRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.CompanyResponseDTO;
import br.com.phricardo.schedulingtechnicians.dto.update.CompanyUpdateDTO;
import br.com.phricardo.schedulingtechnicians.entities.Company;
import br.com.phricardo.schedulingtechnicians.service.CompanyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/company")
@Tag(name = "Company", description = "Endpoints to manage company information")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<CompanyResponseDTO> register(@Valid @RequestBody CompanyRequestDTO companyRequestDTO) {
        return companyService.register(companyRequestDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDTO> customerById(@PathVariable Long id){
        return companyService.getCompanyById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody CompanyUpdateDTO companyUpdateDTO) {
        return companyService.update(id, companyUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return companyService.deleteCompanyById(id);
    }

    @GetMapping
    public List<CompanyResponseDTO> getAllCompanies() {
       return companyService.getAllCompanies();
    }
}
