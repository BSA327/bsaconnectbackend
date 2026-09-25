package com.company.bsaadmin.service;


import org.springframework.stereotype.Service;

import com.company.bsaadmin.entity.Customer;
import com.company.bsaadmin.repository.CustomerRepository;

@Service
public class CustomerService    extends GenericService<Customer, Long>  {

	private final  CustomerRepository repository;

	public CustomerService(CustomerRepository repository) {
		super(repository);
	    this.repository = repository;
	}


	
}

