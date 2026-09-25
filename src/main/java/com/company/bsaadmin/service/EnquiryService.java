package com.company.bsaadmin.service;


import org.springframework.stereotype.Service;

import com.company.bsaadmin.entity.Enquiry;
import com.company.bsaadmin.repository.EnquiryRepository;

@Service
public class EnquiryService  extends GenericService<Enquiry, Long>  {

	private final  EnquiryRepository repository;

	public EnquiryService(EnquiryRepository repository) {
		super(repository);
	    this.repository = repository;
	}




	
}

