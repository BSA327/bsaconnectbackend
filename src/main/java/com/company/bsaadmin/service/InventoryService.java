package com.company.bsaadmin.service;


import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.company.bsaadmin.entity.Inventory;
import com.company.bsaadmin.repository.InventoryRepository;

@Service
public class InventoryService    extends GenericService<Inventory>  {

	private final  InventoryRepository repository;

	public InventoryService(InventoryRepository repository) {
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

