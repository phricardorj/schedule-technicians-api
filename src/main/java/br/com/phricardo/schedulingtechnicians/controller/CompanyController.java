package br.com.phricardo.schedulingtechnicians.controller;

import br.com.phricardo.schedulingtechnicians.dto.request.CompanyRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.CompanyResponseDTO;
import br.com.phricardo.schedulingtechnicians.dto.update.CompanyUpdateDTO;
import br.com.phricardo.schedulingtechnicians.service.CompanyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/company")
@Tag(name = "Company", description = "Endpoints to manage company information")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<CompanyResponseDTO> register(@Valid @RequestBody CompanyRequestDTO companyRequestDTO) {
        return companyService.register(companyRequestDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDTO> companyById(@PathVariable Long id){
        return companyService.getCompanyById(id);
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<CompanyResponseDTO> update(@PathVariable Long id, @Valid @RequestBody CompanyUpdateDTO companyUpdateDTO) {
        return companyService.update(id, companyUpdateDTO);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public void delete(@PathVariable Long id){
        companyService.deleteCompanyById(id);
    }

    @GetMapping
    public Page<CompanyResponseDTO> getAllCompanies(Pageable pageable) {
       return companyService.getAllCompanies(pageable);
    }
}
