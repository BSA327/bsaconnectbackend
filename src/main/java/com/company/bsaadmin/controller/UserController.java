package com.company.bsaadmin.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.bsaadmin.dto.request.RegisterUserRequest;
import com.company.bsaadmin.entity.Employee;
import com.company.bsaadmin.entity.User;
import com.company.bsaadmin.service.EmployeeService;
import com.company.bsaadmin.service.UserService;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
	
	@Autowired
    private UserService repo;
	
	@Autowired
    private EmployeeService empService;;
	
	@Autowired
	private PasswordEncoder passwordEncoder;


    @GetMapping
    public List<User> list() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RegisterUserRequest user) {

        if (repo.existsByUserName(user.getUserName())) {
            return ResponseEntity
                    .badRequest()
                    .body("Username already exists");
        }
        
        
        if(user.getPassword()!=null) {
			Employee employee = new Employee();
			employee.setName(user.getName());
			employee.setGender(user.getGender());
			employee.setMobileNumber(user.getMobileNumber());
			employee.setEmailId(user.getEmailId());
			employee.setCommunicationAddress(user.getCommunicationAddress());
			employee.setPermanentAddress(user.getPermanentAddress());
			employee.setCity(user.getCity());
			employee.setState(user.getState());
			employee.setCountry(user.getCountry());
			employee.setAadharNumber(user.getAadharNumber());
			employee.setPanNumber(user.getPanNumber());
			employee.setJoiningDate(user.getJoiningDate());
			employee.setCreatedBy(user.getCreatedBy());
			employee.setCreatedDate(LocalDate.now());
			employee.setActive(true);
			Employee emp=empService.save(employee);
			
			User useren = new User();
			useren.setEmployeeId(emp.getId());
			useren.setUserName(user.getUserName());
			useren.setPassword(passwordEncoder.encode(user.getPassword()));
			useren.setRole(User.Role.valueOf(user.getRole()));
			useren.setCreatedBy(user.getCreatedBy());
			useren.setCreatedDate(LocalDate.now());
			useren.setActive(true);
			repo.save(useren);
			
			return ResponseEntity.ok("Saved");
		}
		return ResponseEntity
                    .badRequest()
                    .body("No Complete information");

    }

    
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody RegisterUserRequest user) {
        User u = repo.findById(id).get();
        
        u.getEmployee().setName(user.getName());
        u.getEmployee().setGender(user.getGender());
        u.getEmployee().setMobileNumber(user.getMobileNumber());
        u.getEmployee().setEmailId(user.getEmailId());
        u.getEmployee().setCommunicationAddress(user.getCommunicationAddress());
        u.getEmployee().setPermanentAddress(user.getPermanentAddress());
        u.getEmployee().setCity(user.getCity());
        u.getEmployee().setState(user.getState());
        u.getEmployee().setCountry(user.getCountry());
        u.getEmployee().setAadharNumber(user.getAadharNumber());
        u.getEmployee().setPanNumber(user.getPanNumber());
        u.getEmployee().setJoiningDate(user.getJoiningDate());
        u.getEmployee().setCreatedBy(user.getCreatedBy());
        u.getEmployee().setCreatedDate(LocalDate.now());
		empService.save(u.getEmployee());
        return repo.save(u);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
       // repo.deleteById(id);
    }
}