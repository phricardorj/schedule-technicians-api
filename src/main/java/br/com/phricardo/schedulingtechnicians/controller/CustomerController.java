package br.com.phricardo.schedulingtechnicians.controller;

import br.com.phricardo.schedulingtechnicians.dto.request.CustomerRequestDTO;
import br.com.phricardo.schedulingtechnicians.dto.response.CustomerResponseDTO;
import br.com.phricardo.schedulingtechnicians.dto.update.CustomerUpdateDTO;
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
    public ResponseEntity<CustomerResponseDTO> customerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody CustomerUpdateDTO customerUpdateDTO) {
        return customerService.update(id, customerUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return customerService.deleteCustomerById(id);
    }
}
