package com.company.bsaadmin.controller;

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

import com.company.bsaadmin.dto.request.PasswordRequest;
import com.company.bsaadmin.dto.request.RegisterUserRequest;
import com.company.bsaadmin.entity.Employee;
import com.company.bsaadmin.entity.User;
import com.company.bsaadmin.service.EmployeeService;
import com.company.bsaadmin.service.UserService;
import com.company.bsaadmin.util.SecurityUtils;

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

        if (repo.existsByUserName(user.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Username already exists");
        }
        
        
        if(user.getPassword()!=null) {
			Employee employee = new Employee();
			employee.setName(user.getName());
			employee.setGender(user.getGender());
			employee.setMobileNumber(user.getPhone());
			employee.setEmailId(user.getEmail());
			employee.setCommunicationAddress(user.getCommunicationAddress());
			employee.setAadharNumber(user.getAadharNumber());
			employee.setJoiningDate(user.getJoiningDate());
			Employee emp=empService.save(employee);
			
			User useren = new User();
			useren.setEmployeeId(emp.getId());
			useren.setUserName(user.getUsername());
			useren.setPassword(passwordEncoder.encode(user.getPassword()));
			useren.setRole(User.Role.valueOf(user.getRole()));
			repo.save(useren);
			
			return ResponseEntity.ok("Saved");
		}
		return ResponseEntity
                    .badRequest()
                    .body("No Complete information");

    }

    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody RegisterUserRequest user) {
        User u = repo.findById(id);
        if(u==null || u.getId()==null || u.getEmployee()==null || u.getEmployeeId()==null) {
        	return ResponseEntity
                    .badRequest()
                    .body("No User found for the userid");

        }
        u.getEmployee().setName(user.getName());
        u.getEmployee().setGender(user.getGender());
        u.getEmployee().setMobileNumber(user.getPhone());
        u.getEmployee().setEmailId(user.getEmail());
        u.getEmployee().setCommunicationAddress(user.getCommunicationAddress());
        u.getEmployee().setAadharNumber(user.getAadharNumber());
        u.getEmployee().setJoiningDate(user.getJoiningDate());
		empService.save( u.getEmployee());
		
		u.setUserName(user.getUsername());
		u.setRole(User.Role.valueOf(user.getRole()));
		u=repo.save(u);
      
        return ResponseEntity.ok().body(u);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
       // repo.deleteById(id);
    }
    
    
    
    @PutMapping("/changepassword")
	public ResponseEntity<?> changePassword(@RequestBody PasswordRequest request) {


        User user = repo.findByUserNameAndActive(SecurityUtils.getUsername());

        // Check current password
        if (!passwordEncoder.matches(
        		request.getOldPassword(),
                user.getPassword())) {

            throw new RuntimeException(
                    "Current password is incorrect."
            );
        }

        // Optional validation
        if (request.getNewPassword() == null ||
        		request.getNewPassword() .trim().isEmpty()) {

            throw new RuntimeException(
                    "New password is required."
            );
        }

      

        // Prevent same password
        if (passwordEncoder.matches(
        		request.getNewPassword() ,
                user.getPassword())) {

            throw new RuntimeException(
                    "New password must be different from current password."
            );
        }

        // Encode and save
        user.setPassword(
                passwordEncoder.encode(request.getNewPassword() )
        );

        repo.save(user);

	    return ResponseEntity.ok("Password updated successfully.");
	    
	}

}