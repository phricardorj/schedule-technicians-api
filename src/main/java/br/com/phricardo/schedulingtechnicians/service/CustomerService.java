package br.com.phricardo.schedulingtechnicians.service;

import br.com.phricardo.schedulingtechnicians.dto.request.CustomerRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.request.mapper.CustomerRequestMapper;
import br.com.phricardo.schedulingtechnicians.dto.response.CustomerResponseDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.mapper.CustomerResponseMapper;
import br.com.phricardo.schedulingtechnicians.dto.update.CustomerUpdateDTO;
import br.com.phricardo.schedulingtechnicians.dto.update.mapper.CustomerUpdateMapper;
import br.com.phricardo.schedulingtechnicians.exception.RegistrationException;
import br.com.phricardo.schedulingtechnicians.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

import static java.util.Optional.*;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerRequestMapper requestMapper;
    private final CustomerResponseMapper responseMapper;
    private final CustomerUpdateMapper updateMapper;
    private final LocationService locationService;

    @Transactional
    public ResponseEntity<CustomerResponseDTO> register(CustomerRequestDTO dto) {
        return of(requestMapper.from(dto))
                .map(repository::save)
                .map(saved ->
                        ResponseEntity.created(
                                        URI.create(locationService.buildLocation("customer/" + saved.getId())))
                                .body(responseMapper.from(saved))
                )
                .orElseThrow(() -> new RegistrationException("Failed to register. Please verify the provided data and try again."));
    }

    public ResponseEntity<CustomerResponseDTO> getCustomerById(Long id) {
        return repository.findById(id)
                .map(responseMapper::from)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + id));
    }

    @Transactional
    public void deleteCustomerById(Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + id)));
    }

    @Transactional
    public ResponseEntity<CustomerResponseDTO> update(Long id, CustomerUpdateDTO customerUpdateDTO) {
        return repository.findById(id)
                .map(customer -> {
                    updateMapper.updateCustomerFromDTO(customerUpdateDTO, customer);
                    return repository.save(customer);
                })
                .map(updated ->
                        ResponseEntity.created(
                                        URI.create(locationService.buildLocation("customer/" + updated.getId())))
                                .body(responseMapper.from(updated)))
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + id));
    }
}
