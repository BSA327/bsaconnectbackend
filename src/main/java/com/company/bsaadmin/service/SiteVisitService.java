package com.company.bsaadmin.service;


import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.company.bsaadmin.entity.SiteVisit;
import com.company.bsaadmin.repository.SiteVisitRepository;

@Service
public class SiteVisitService   extends GenericService<SiteVisit, Long>  {

	private final  SiteVisitRepository repository;

	public SiteVisitService(SiteVisitRepository repository) {
		super(repository);
	    this.repository = repository;
	}

	
	public List<SiteVisit> findByBdmIdAndVisitDateBetween(Long bdmId, LocalDate from, LocalDate to) {
		return repository.findByBdmIdAndVisitDateBetween( bdmId,  from,  to);
	}


	public  List<SiteVisit> findByInventoryId(Long inventoryId) {
		return repository.findByInventoryId( inventoryId);
	}

	
}

