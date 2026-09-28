package com.company.bsaadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.bsaadmin.entity.Project;
import com.company.bsaadmin.service.ProjectService;

@CrossOrigin
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

	@Autowired
	private ProjectService service;


	@GetMapping
	public List<Project> list() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Project get(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	public Project create(@RequestBody Project input) {
		input.setId(null);
		return service.save(input);
	}

	@PutMapping("/{id}")
	public Project update(@PathVariable Long id, @RequestBody Project input) {
		input.setId(id);
		return service.update(id,input);
	}
}
