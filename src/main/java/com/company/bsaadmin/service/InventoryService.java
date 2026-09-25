package com.company.bsaadmin.service;


import org.springframework.stereotype.Service;

import com.company.bsaadmin.entity.Inventory;
import com.company.bsaadmin.repository.InventoryRepository;

@Service
public class InventoryService    extends GenericService<Inventory, Long>  {

	private final  InventoryRepository repository;

	public InventoryService(InventoryRepository repository) {
		super(repository);
	    this.repository = repository;
	}


	
}

