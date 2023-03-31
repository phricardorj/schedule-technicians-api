package br.com.phricardo.schedulingtechnicians.controller;

import br.com.phricardo.schedulingtechnicians.dto.request.CustomerRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.CustomerResponseDTO;
import br.com.phricardo.schedulingtechnicians.entities.Customer;
import br.com.phricardo.schedulingtechnicians.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> register(@Valid @RequestBody CustomerRequestDTO customerRequestDTO) {
        return customerService.register(customerRequestDTO);
    }

    @GetMapping("/{id}")
    public Customer customerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }
}
