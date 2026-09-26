package com.company.bsaadmin.service;


import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.company.bsaadmin.entity.Enquiry;
import com.company.bsaadmin.repository.EnquiryRepository;

@Service
public class EnquiryService  extends GenericService<Enquiry>  {

	private final  EnquiryRepository repository;

	public EnquiryService(EnquiryRepository repository) {
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

