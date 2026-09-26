package com.company.bsaadmin.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.company.bsaadmin.entity.InventoryMedia;
import com.company.bsaadmin.repository.InventoryMediaRepository;

@Service
public class InventoryMediaService   extends GenericService<InventoryMedia>  {

	private final  InventoryMediaRepository repository;

	public InventoryMediaService(InventoryMediaRepository repository) {
		super(repository);
	    this.repository = repository;
	}

	public List<InventoryMedia> findByInventoryId(Long inventoryId) {
		return repository.findByInventoryId(inventoryId);
	}


	
}

