package com.company.bsaadmin.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.bsaadmin.dto.request.SiteVisitRequest;
import com.company.bsaadmin.entity.SiteVisit;
import com.company.bsaadmin.service.EnquiryService;
import com.company.bsaadmin.service.InventoryService;
import com.company.bsaadmin.service.SiteVisitService;
import com.company.bsaadmin.service.UserService;
@CrossOrigin
@RestController
@RequestMapping("/api/site-visits")
public class SiteVisitController {
	
	@Autowired
    private SiteVisitService visits;
	
	@Autowired
    private  InventoryService inventory;
	
	@Autowired
    private  EnquiryService enquiries;
	
	@Autowired
    private  UserService users;

   

    @GetMapping
    public List<SiteVisit> list() {
        return visits.findAll();
    }

    @GetMapping("/{id}")
    public SiteVisit get(@PathVariable Long id) {
        return visits.findById(id);
    }

    @PostMapping
    public SiteVisit create(@RequestBody SiteVisitRequest r) {
        SiteVisit v = new SiteVisit();
        v.setInventory(inventory.findById(r.getInventoryId()));
        if (r.getEnquiryId()!=null) v.setEnquiry(enquiries.findById(r.getEnquiryId()));
        v.setUser(users.findById(r.getUserId()));
        v.setVisitDate(r.getDate());
        v.setRemarks(r.getRemarks());
        v.setStatus(SiteVisit.Status.SCHEDULED);
        return visits.save(v);
    }

    @PutMapping("/{id}")
    public SiteVisit update(@PathVariable Long id, @RequestBody SiteVisitRequest r) {
        SiteVisit v = visits.findById(id);
        v.setInventory(inventory.findById(r.getInventoryId()));
        v.setEnquiry(r.getEnquiryId()==null ? null : enquiries.findById(r.getEnquiryId()));
        v.setUser(users.findById(r.getUserId()));
        v.setVisitDate(r.getDate());
        v.setRemarks(r.getRemarks());
        return visits.update(id,v);
    }

    @GetMapping("/search")
    public List<SiteVisit> search(@RequestParam(required=false) Long userId,
                                  @RequestParam(required=false) LocalDate fromDate,
                                  @RequestParam(required=false) LocalDate toDate) {
        if (userId == null || fromDate == null || toDate == null) return visits.findAll();
        return visits.findByUserIdAndVisitDateBetween(userId, fromDate, toDate);
    }
}