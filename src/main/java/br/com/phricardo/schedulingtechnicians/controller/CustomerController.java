package br.com.phricardo.schedulingtechnicians.controller;

import br.com.phricardo.schedulingtechnicians.dto.CustomerDTO;
import br.com.phricardo.schedulingtechnicians.entities.Customer;
import br.com.phricardo.schedulingtechnicians.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public void register(@Valid @RequestBody CustomerDTO customerDTO) {
        customerService.register(customerDTO);
    }

    @GetMapping("/{id}")
    public Customer customerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }
}
