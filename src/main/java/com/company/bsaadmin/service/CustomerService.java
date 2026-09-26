package com.company.bsaadmin.service;


import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.company.bsaadmin.entity.Customer;
import com.company.bsaadmin.repository.CustomerRepository;

@Service
public class CustomerService    extends GenericService<Customer>  {

	private final  CustomerRepository repository;

	public CustomerService(CustomerRepository repository) {
		super(repository);
	    this.repository = repository;
	}

	public long countByActiveTrue(LocalDate fromDate, LocalDate toDate) {
		return repository.countByActiveTrueAndCreatedDateBetween(fromDate, toDate);
	}

	public long countByActiveTrueAndCreatedBy(Long userId, LocalDate fromDate, LocalDate toDate) {
		return repository.countByActiveTrueAndCreatedByAndCreatedDateBetween(userId, fromDate, toDate);
	}

	
}

