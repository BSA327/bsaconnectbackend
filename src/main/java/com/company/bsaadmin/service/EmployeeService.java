package com.company.bsaadmin.service;


import org.springframework.stereotype.Service;

import com.company.bsaadmin.entity.Employee;
import com.company.bsaadmin.repository.EmployeeRepository;

@Service
public class EmployeeService  extends GenericService<Employee, Long>  {

	private final  EmployeeRepository repository;

	public EmployeeService(EmployeeRepository repository) {
		super(repository);
	    this.repository = repository;
	}

}

