package br.com.phricardo.schedulingtechnicians.service;

import br.com.phricardo.schedulingtechnicians.dto.request.CustomerRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.request.mapper.CustomerRequestMapper;
import br.com.phricardo.schedulingtechnicians.dto.response.CustomerResponseDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.mapper.CustomerResponseMapper;
import br.com.phricardo.schedulingtechnicians.dto.update.CustomerUpdateDTO;
import br.com.phricardo.schedulingtechnicians.dto.update.mapper.CustomerUpdateMapper;
import br.com.phricardo.schedulingtechnicians.entities.Customer;
import br.com.phricardo.schedulingtechnicians.exception.RegistrationException;
import br.com.phricardo.schedulingtechnicians.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.CREATED;

@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerRequestMapper requestMapper;
    private final CustomerResponseMapper responseMapper;
    private final CustomerUpdateMapper updateMapper;
    private final LocationService locationService;

    public CustomerService(CustomerRepository repository, CustomerRequestMapper requestMapper, CustomerResponseMapper responseMapper, CustomerUpdateMapper updateMapper, LocationService locationService) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.updateMapper = updateMapper;
        this.locationService = locationService;
    }

    @Transactional
    public ResponseEntity<CustomerResponseDTO> register(CustomerRequestDTO dto) throws RegistrationException {
        Customer customer = requestMapper.from(dto);
        Customer savedCustomer = repository.save(customer);

        if(savedCustomer.getId() == null)
            throw new RegistrationException("Unable to register the customer. An internal error occurred in the API.");

        CustomerResponseDTO customerResponseDTO = responseMapper.from(savedCustomer);
        String location = locationService.buildLocation("customer/" + customer.getId());

        return ResponseEntity
                .status(CREATED)
                .header("Location", location)
                .body(customerResponseDTO);
    }

    public ResponseEntity<CustomerResponseDTO> getCustomerById(Long id) {
        Customer customer = repository.findById(id).orElse(null);
        if(nonNull(customer)) {
            CustomerResponseDTO customerResponseDTO = responseMapper.from(customer);
            return ResponseEntity
                    .status(OK)
                    .body(customerResponseDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<Void> deleteCustomerById(Long id) {
        Customer customer = repository.findById(id).orElse(null);
        if(nonNull(customer)) {
            repository.delete(customer);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<Void> update(Long id, CustomerUpdateDTO customerUpdateDTO) {
        Customer customer = repository.findById(id).orElse(null);
        if(nonNull(customer)) {
            updateMapper.updateCustomerFromDTO(customerUpdateDTO, customer);
            repository.save(customer);
            return ResponseEntity
                    .status(OK)
                    .header("Location", locationService.buildLocation("customer/" + customer.getId()))
                    .build();
        }
        return ResponseEntity.notFound().build();
    }
}
