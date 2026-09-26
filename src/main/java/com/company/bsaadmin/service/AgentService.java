package com.company.bsaadmin.service;


import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.company.bsaadmin.entity.Agent;
import com.company.bsaadmin.repository.AgentRepository;

@Service
public class AgentService   extends GenericService<Agent>  {

	private final AgentRepository repository;

	public AgentService(AgentRepository repository) {
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

