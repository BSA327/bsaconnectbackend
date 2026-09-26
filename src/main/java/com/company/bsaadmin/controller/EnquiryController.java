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

import com.company.bsaadmin.entity.Enquiry;
import com.company.bsaadmin.service.EnquiryService;

@CrossOrigin
@RestController
@RequestMapping("/api/enquiries")
public class EnquiryController {
   
	@Autowired
	private EnquiryService service;


    @GetMapping
    public List<Enquiry> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Enquiry get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Enquiry create(@RequestBody Enquiry input) {
        input.setId(null);
        return service.save(input);
    }

    @PutMapping("/{id}")
    public Enquiry update(@PathVariable Long id, @RequestBody Enquiry input) {
        input.setId(id);
        return service.update(id,input);
    }
}
