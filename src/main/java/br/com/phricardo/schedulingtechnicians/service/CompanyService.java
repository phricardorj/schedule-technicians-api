package br.com.phricardo.schedulingtechnicians.service;

import br.com.phricardo.schedulingtechnicians.dto.request.CompanyRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.request.mapper.CompanyRequestMapper;
import br.com.phricardo.schedulingtechnicians.dto.response.CompanyResponseDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.mapper.CompanyResponseMapper;
import br.com.phricardo.schedulingtechnicians.dto.update.CompanyUpdateDTO;
import br.com.phricardo.schedulingtechnicians.dto.update.mapper.CompanyUpdateMapper;
import br.com.phricardo.schedulingtechnicians.exception.RegistrationException;
import br.com.phricardo.schedulingtechnicians.repository.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

import static java.util.Optional.*;

@Service
public class CompanyService {

    private final CompanyRepository repository;
    private final CompanyRequestMapper requestMapper;
    private final CompanyResponseMapper responseMapper;
    private final CompanyUpdateMapper updateMapper;
    private final LocationService locationService;

    public CompanyService(CompanyRepository repository, CompanyRequestMapper requestMapper, CompanyResponseMapper responseMapper, CompanyUpdateMapper updateMapper, LocationService locationService) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.updateMapper = updateMapper;
        this.locationService = locationService;
    }

    public ResponseEntity<CompanyResponseDTO> getCompanyById(Long id) {
        return repository.findById(id)
                .map(responseMapper::from)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Company not found with ID: " + id));
    }

    @Transactional
    public void deleteCompanyById(Long id) {
         final var company = repository.findById(id)
                 .orElseThrow(() -> new EntityNotFoundException("Company not found with ID: " + id));
         repository.delete(company);
    }

    @Transactional
    public ResponseEntity<CompanyResponseDTO> update(Long id, CompanyUpdateDTO companyUpdateDTO) {
      return repository.findById(id)
                .map(company -> {
                    updateMapper.updateCompanyFromDTO(companyUpdateDTO, company);
                    return repository.save(company);
                })
                .map(responseMapper::from)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Company not found with ID: " + id));
    }

    @Transactional
    public ResponseEntity<CompanyResponseDTO> register(CompanyRequestDTO dto) {
        return of(requestMapper.from(dto))
                .map(repository::save)
                .map(savedCompany ->
                     ResponseEntity.created(
                             URI.create(locationService.buildLocation("company/" + savedCompany.getId())))
                             .body(responseMapper.from(savedCompany))
                )
                .orElseThrow(() -> new RegistrationException("Failed to register. Please verify the provided data and try again."));
    }

    public Page<CompanyResponseDTO> getAllCompanies(Pageable pageable) {
        return repository.findAll(pageable).map(responseMapper::from);
    }
}
