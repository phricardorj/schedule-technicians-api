package br.com.phricardo.schedulingtechnicians.controller;

import br.com.phricardo.schedulingtechnicians.dto.CustomerDTO;
import br.com.phricardo.schedulingtechnicians.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
