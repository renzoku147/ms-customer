package com.java.everis.client.controller;

import com.java.everis.client.entity.Customer;
import com.java.everis.client.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RefreshScope
@RestController
@Slf4j
@RequestMapping("/customer")
public class CustomerController {
//Hola Commit ..
    @Autowired
    CustomerService customerService;

    @GetMapping("/list")
    public Flux<Customer> list(){
        return customerService.findAll();
    }

    @GetMapping("/find/{id}")
    public Mono<Customer> findById(@PathVariable String id){
        return customerService.findById(id);
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<Customer>> create(@RequestBody Customer c){
        return customerService.create(c)
                .map(savedCustomer -> new ResponseEntity<>(savedCustomer , HttpStatus.CREATED));
    }

    @PutMapping("/update")
    public Mono<ResponseEntity<Customer>> update(@RequestBody Customer c) {
        return customerService.update(c)
                .map(savedCustomer -> new ResponseEntity<>(savedCustomer, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<String>> delete(@PathVariable String id) {
        return customerService.delete(id)
                .filter(deleteCustomer -> deleteCustomer)
                .map(deleteCustomer -> new ResponseEntity<>("Customer Deleted", HttpStatus.ACCEPTED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
