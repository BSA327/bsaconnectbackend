package com.company.bsaadmin.service;


import org.springframework.stereotype.Service;

import com.company.bsaadmin.entity.Agent;
import com.company.bsaadmin.repository.AgentRepository;

@Service
public class AgentService   extends GenericService<Agent, Long>  {

	private final AgentRepository repository;

	public AgentService(AgentRepository repository) {
		super(repository);
	    this.repository = repository;
	}

	
}

