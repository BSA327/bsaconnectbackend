package com.company.bsaadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.bsaadmin.entity.Customer;
import com.company.bsaadmin.service.CustomerService;

@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	@Autowired
    private CustomerService service;

    @GetMapping
    public List<Customer> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Customer get(@PathVariable Long id) {
        return service.findById(id).get();
    }

    @PostMapping
    public Customer create(@RequestBody Customer input) {
        input.setId(null);
        return service.save(input);
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @RequestBody Customer input) {
        input.setId(id);
        return service.save(input);
    }
}
