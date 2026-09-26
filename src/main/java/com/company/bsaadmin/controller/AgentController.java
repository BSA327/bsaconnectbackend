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

import com.company.bsaadmin.entity.Agent;
import com.company.bsaadmin.service.AgentService;

@CrossOrigin
@RestController
@RequestMapping("/api/agents")
public class AgentController {
   
	@Autowired
	private AgentService service;

   
    @GetMapping
    public List<Agent> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Agent get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Agent create(@RequestBody Agent input) {
        input.setId(null);
        return service.save(input);
    }

    @PutMapping("/{id}")
    public Agent update(@PathVariable Long id, @RequestBody Agent input) {
        input.setId(id);
        return service.update(id,input);
    }
}
