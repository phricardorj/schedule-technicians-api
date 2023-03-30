package br.com.phricardo.schedulingtechnicians.service;

import br.com.phricardo.schedulingtechnicians.dto.CustomerDTO;
import br.com.phricardo.schedulingtechnicians.entities.Customer;
import br.com.phricardo.schedulingtechnicians.mapper.CustomerMapper;
import br.com.phricardo.schedulingtechnicians.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public CustomerService(CustomerRepository repository, CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void register(CustomerDTO dto) {
        Customer customer = mapper.customerDTOtoCustomer(dto);
        repository.save(customer);
    }

}
