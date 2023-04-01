package br.com.phricardo.schedulingtechnicians.service;

import br.com.phricardo.schedulingtechnicians.dto.request.CompanyRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.request.mapper.CompanyRequestMapper;
import br.com.phricardo.schedulingtechnicians.dto.response.CompanyResponseDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.mapper.CompanyResponseMapper;
import br.com.phricardo.schedulingtechnicians.dto.update.CompanyUpdateDTO;
import br.com.phricardo.schedulingtechnicians.dto.update.mapper.CompanyUpdateMapper;
import br.com.phricardo.schedulingtechnicians.entities.Company;
import br.com.phricardo.schedulingtechnicians.repository.CompanyRepository;
import br.com.phricardo.schedulingtechnicians.util.LocationUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Service
public class CompanyService {

    private final CompanyRepository repository;
    private final CompanyRequestMapper requestMapper;
    private final CompanyResponseMapper responseMapper;
    private final CompanyUpdateMapper updateMapper;
    private final LocationUtil locationUtil;

    public CompanyService(CompanyRepository repository, CompanyRequestMapper requestMapper, CompanyResponseMapper responseMapper, CompanyUpdateMapper updateMapper, LocationUtil locationUtil) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.updateMapper = updateMapper;
        this.locationUtil = locationUtil;
    }

    public ResponseEntity<CompanyResponseDTO> register(CompanyRequestDTO dto) {
        Company company = requestMapper.from(dto);
        company = repository.save(company);
        CompanyResponseDTO companyResponseDTO = responseMapper.from(company);
        String location = locationUtil.buildLocation("customer/" + company.getId());

        return ResponseEntity
                .status(CREATED)
                .header("Location", location)
                .body(companyResponseDTO);
    }

    public ResponseEntity<CompanyResponseDTO> getCompanyById(Long id) {
        Company company = repository.findById(id).orElse(null);
        if(nonNull(company)) {
            CompanyResponseDTO companyResponseDTO = responseMapper.from(company);
            return ResponseEntity
                    .status(OK)
                    .body(companyResponseDTO);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> deleteCompanyById(Long id) {
        Company company = repository.findById(id).orElse(null);
        if(nonNull(company)) {
            repository.delete(company);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> update(Long id, CompanyUpdateDTO companyUpdateDTO) {
        Company company = repository.findById(id).orElse(null);
        if(nonNull(company)) {
            updateMapper.updateCompanyFromDTO(companyUpdateDTO, company);
            repository.save(company);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
