package com.java.everis.client.service.impl;

import com.java.everis.client.entity.Customer;
import com.java.everis.client.repository.CustomerRepository;
import com.java.everis.client.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Mono<Customer> create(Customer customer) {

        return customerRepository.save(customer);
    }

    @Override
    public Flux<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Mono<Customer> findById(String id) {
        return customerRepository.findById(id) ;
    }

    @Override
    public Mono<Customer> update(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Mono<Boolean> delete(String id) {
        return customerRepository.findById(id)
                .flatMap(
                        deleteCustomer -> customerRepository.delete(deleteCustomer)
                                .then(Mono.just(Boolean.TRUE))
                )
                .defaultIfEmpty(Boolean.FALSE);
    }
}
