package com.company.bsaadmin.service;


import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.company.bsaadmin.entity.Task;
import com.company.bsaadmin.repository.TaskRepository;

@Service
public class TaskService   extends GenericService<Task, Long>  {

	private final  TaskRepository repository;

	public TaskService(TaskRepository repository) {
		super(repository);
	    this.repository = repository;
	}


	public List<Task> findByUserIdAndDateBetween(Long userId, LocalDate from, LocalDate to){
		return repository.findByUserIdAndDateBetween(userId, from,  to);
	}

}

