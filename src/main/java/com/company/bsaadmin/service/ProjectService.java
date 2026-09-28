package com.company.bsaadmin.service;


import org.springframework.stereotype.Service;

import com.company.bsaadmin.entity.Project;
import com.company.bsaadmin.repository.ProjectRepository;

@Service
public class ProjectService    extends GenericService<Project>  {

	private final  ProjectRepository repository;

	public ProjectService(ProjectRepository repository) {
		super(repository);
	    this.repository = repository;
	}


	
}

