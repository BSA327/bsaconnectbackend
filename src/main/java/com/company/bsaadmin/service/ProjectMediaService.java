package com.company.bsaadmin.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.company.bsaadmin.entity.ProjectMedia;
import com.company.bsaadmin.repository.ProjectMediaRepository;

@Service
public class ProjectMediaService    extends GenericService<ProjectMedia>  {

	private final  ProjectMediaRepository repository;

	public ProjectMediaService(ProjectMediaRepository repository) {
		super(repository);
	    this.repository = repository;
	}

	public List<ProjectMedia> findByProjectId(Long projectId) {
		return repository.findByProjectId(projectId);
	}


	
}

