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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

import static java.util.Optional.*;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;
    private final CompanyRequestMapper requestMapper;
    private final CompanyResponseMapper responseMapper;
    private final CompanyUpdateMapper updateMapper;
    private final LocationService locationService;

    public ResponseEntity<CompanyResponseDTO> getCompanyById(final Long id) {
        return repository.findById(id)
                .map(responseMapper::from)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Company not found with ID: " + id));
    }

    @Transactional
    public ResponseEntity<CompanyResponseDTO> register(final CompanyRequestDTO dto) {
        return of(requestMapper.from(dto))
                .map(repository::save)
                .map(savedCompany ->
                        ResponseEntity.created(
                                        URI.create(locationService.buildLocation("company/" + savedCompany.getId())))
                                .body(responseMapper.from(savedCompany))
                )
                .orElseThrow(() -> new RegistrationException("Failed to register. Please verify the provided data and try again."));
    }

    @Transactional
    public void deleteCompanyById(final Long id) {
         final var company = repository.findById(id)
                 .orElseThrow(() -> new EntityNotFoundException("Company not found with ID: " + id));
         repository.delete(company);
    }

    @Transactional
    public ResponseEntity<CompanyResponseDTO> update(final Long id, final CompanyUpdateDTO companyUpdateDTO) {
      return repository.findById(id)
                .map(company -> {
                    updateMapper.updateCompanyFromDTO(companyUpdateDTO, company);
                    return repository.save(company);
                })
              .map(updated ->
                      ResponseEntity.created(
                                      URI.create(locationService.buildLocation("company/" + updated.getId())))
                              .body(responseMapper.from(updated)))
                .orElseThrow(() -> new EntityNotFoundException("Company not found with ID: " + id));
    }

    public Page<CompanyResponseDTO> getAllCompanies(Pageable pageable) {
        return repository.findAll(pageable).map(responseMapper::from);
    }
}
