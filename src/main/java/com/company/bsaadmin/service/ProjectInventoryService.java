package com.company.bsaadmin.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.company.bsaadmin.entity.ProjectInventory;
import com.company.bsaadmin.repository.ProjectInventoryRepository;

@Service
public class ProjectInventoryService    extends GenericService<ProjectInventory>  {

	private final  ProjectInventoryRepository repository;

	public ProjectInventoryService(ProjectInventoryRepository repository) {
		super(repository);
	    this.repository = repository;
	}

	public List<ProjectInventory> findByProjectId(Long projectId) {
		return repository.findByProjectId(projectId);
	}


	
}

