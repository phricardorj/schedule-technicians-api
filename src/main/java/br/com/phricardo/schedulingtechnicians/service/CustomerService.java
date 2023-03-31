package br.com.phricardo.schedulingtechnicians.service;

import br.com.phricardo.schedulingtechnicians.dto.request.CustomerRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.request.mapper.CustomerRequestMapper;
import br.com.phricardo.schedulingtechnicians.dto.response.CustomerResponseDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.mapper.CustomerResponseMapper;
import br.com.phricardo.schedulingtechnicians.dto.update.CustomerUpdateDTO;
import br.com.phricardo.schedulingtechnicians.dto.update.mapper.CustomerUpdateMapper;
import br.com.phricardo.schedulingtechnicians.entities.Customer;
import br.com.phricardo.schedulingtechnicians.repository.CustomerRepository;
import br.com.phricardo.schedulingtechnicians.util.LocationUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.CREATED;

@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerRequestMapper requestMapper;
    private final CustomerResponseMapper responseMapper;
    private final CustomerUpdateMapper updateMapper;
    private final LocationUtil locationUtil;

    public CustomerService(CustomerRepository repository, CustomerRequestMapper requestMapper, CustomerResponseMapper responseMapper, CustomerUpdateMapper updateMapper, LocationUtil locationUtil) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.updateMapper = updateMapper;
        this.locationUtil = locationUtil;
    }

    public ResponseEntity<CustomerResponseDTO> register(CustomerRequestDTO dto) {
        Customer customer = requestMapper.from(dto);
        customer = repository.save(customer);
        CustomerResponseDTO customerResponseDTO = responseMapper.from(customer);
        String location = locationUtil.buildLocation("customer/" + customer.getId());

        return ResponseEntity
                .status(CREATED)
                .header("Location", location)
                .body(customerResponseDTO);
    }

    public ResponseEntity<CustomerResponseDTO> getCustomerById(Long id) {
        Optional<Customer> optionalCustomer = repository.findById(id);
        Customer customer = optionalCustomer.orElse(null);
        CustomerResponseDTO customerResponseDTO = responseMapper.from(customer);

        return ResponseEntity
                .status(OK)
                .body(customerResponseDTO);
    }

    public ResponseEntity<Void> deleteCustomerById(Long id) {
        Customer customer = repository.findById(id).orElse(null);
        if(nonNull(customer)) {
            repository.delete(customer);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> update(Long id, CustomerUpdateDTO customerUpdateDTO) {
        Customer customer = repository.findById(id).orElse(null);
        if(nonNull(customer)) {
            updateMapper.updateCustomerFromDTO(customerUpdateDTO, customer);
            repository.save(customer);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
