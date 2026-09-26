package com.company.bsaadmin.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.bsaadmin.dto.response.DashboardDto;
import com.company.bsaadmin.service.AgentService;
import com.company.bsaadmin.service.CustomerService;
import com.company.bsaadmin.service.EnquiryService;
import com.company.bsaadmin.service.InventoryService;
import com.company.bsaadmin.util.SecurityUtils;
@CrossOrigin
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {


	@Autowired
    private InventoryService inventoryService;
	
	@Autowired
	private EnquiryService enquiryService;
	
	@Autowired
    private CustomerService customerService;
	
	@Autowired
    private AgentService agentsService;
	
	
	@GetMapping("/fetchstatics")
	public List<DashboardDto> findStatics() {

	    Long userId = SecurityUtils.getUserId();
	    String role=SecurityUtils.getRole();
	    boolean isAdmin=false;
	    if(role.equals("ADMIN")) {
	    	isAdmin=true;
	    }
	  
	    LocalDate fromDate = LocalDate.now()
	            .withDayOfMonth(1);

	    LocalDate toDate = LocalDate.now();
	    
	    
	    List<DashboardDto> list = new ArrayList<>();

	    // -----------------------------------------
	    // Inventory
	    // -----------------------------------------

	    DashboardDto inventory = new DashboardDto();

	    long inventoryCount;

	    if (isAdmin) {
	        inventoryCount = inventoryService.countByActiveTrue(fromDate,toDate);
	    } else {
	        inventoryCount =
	                inventoryService.countByActiveTrueAndCreatedBy(userId,fromDate,toDate);
	    }

	    inventory.setName("Inventories");
	    inventory.setTotalCount(inventoryCount);

	    list.add(inventory);

	    // -----------------------------------------
	    // Enquiry
	    // -----------------------------------------

	    DashboardDto enquiry = new DashboardDto();

	    long enquiryCount;

	    if (isAdmin) {
	        enquiryCount =
	                enquiryService.countByActiveTrue(fromDate,toDate);
	    } else {
	        enquiryCount =
	        		enquiryService.countByActiveTrueAndCreatedBy(userId,fromDate,toDate);
	    }

	    enquiry.setName("Total Enquiry");
	    enquiry.setTotalCount(enquiryCount);

	    list.add(enquiry);

	    // -----------------------------------------
	    // Channel Partners
	    // -----------------------------------------

	    DashboardDto customers = new DashboardDto();

	    long customersCount;

	    if (isAdmin) {
	    	customersCount =
	    			customerService.countByActiveTrue(fromDate,toDate);
	    } else {
	    	customersCount =
	    			customerService.countByActiveTrueAndCreatedBy(userId,fromDate,toDate);
	    }

	    customers.setName("Customers");
	    customers.setTotalCount(customersCount);

	    list.add(customers);

	    // -----------------------------------------
	    // Site Visits
	    // -----------------------------------------

	    DashboardDto agents = new DashboardDto();

	    long agentsCount;

	    if (isAdmin) {
	    	agentsCount =
	    			agentsService.countByActiveTrue(fromDate,toDate);
	    } else {
	    	agentsCount =
	    			agentsService.countByActiveTrueAndCreatedBy(userId,fromDate,toDate);
	    }

	    agents.setName("Agents/CP");
	    agents.setTotalCount(agentsCount);

	    list.add(agents);

	    return list;
	}

}

