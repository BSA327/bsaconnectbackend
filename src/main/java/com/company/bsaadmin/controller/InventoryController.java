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

import com.company.bsaadmin.entity.Inventory;
import com.company.bsaadmin.service.InventoryService;

@CrossOrigin
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	
	@Autowired
    private InventoryService service;

    
    @GetMapping
    public List<Inventory> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Inventory get(@PathVariable Long id) {
        return service.findById(id).get();
    }

    @PostMapping
    public Inventory create(@RequestBody Inventory input) {
        input.setId(null);
        return service.save(input);
    }

    @PutMapping("/{id}")
    public Inventory update(@PathVariable Long id, @RequestBody Inventory input) {
        input.setId(id);
        return service.save(input);
    }
}
