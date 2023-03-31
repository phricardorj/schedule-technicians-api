package br.com.phricardo.schedulingtechnicians.service;

import br.com.phricardo.schedulingtechnicians.dto.request.CustomerRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.request.mapper.CustomerRequestMapper;
import br.com.phricardo.schedulingtechnicians.dto.response.CustomerResponseDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.mapper.CustomerResponseMapper;
import br.com.phricardo.schedulingtechnicians.entities.Customer;
import br.com.phricardo.schedulingtechnicians.repository.CustomerRepository;
import br.com.phricardo.schedulingtechnicians.util.LocationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerRequestMapper requestMapper;
    private final CustomerResponseMapper responseMapper;
    private final LocationUtil locationUtil;

    public CustomerService(CustomerRepository repository, CustomerRequestMapper requestMapper, CustomerResponseMapper responseMapper, LocationUtil locationUtil) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.locationUtil = locationUtil;
    }

    public ResponseEntity<CustomerResponseDTO> register(CustomerRequestDTO dto) {
        Customer customer = requestMapper.from(dto);
        customer = repository.save(customer);
        CustomerResponseDTO customerResponseDTO = responseMapper.from(customer);
        String location = locationUtil.buildLocation("customer/" + customer.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", location)
                .body(customerResponseDTO);
    }

    public Customer getCustomerById(Long id) {
        Optional<Customer> optionalCustomer = repository.findById(id);
        return optionalCustomer.orElse(null);
    }
}
