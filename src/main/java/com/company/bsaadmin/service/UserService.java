package com.company.bsaadmin.service;


import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.company.bsaadmin.entity.User;
import com.company.bsaadmin.repository.UserRepository;

@Service
public class UserService  extends GenericService<User>  {

	private final  UserRepository repository;

	public UserService(UserRepository repository) {
		super(repository);
	    this.repository = repository;
	}
	
	public List<User> findByActive() {
		return repository.findByActive(true);
	}

	public User findByIdAndActive(Long id) {
	    return repository.findByIdAndActive(id,true);
	}
	
	public User authenticate(String userName, String password) {
		return repository.findByUserNameAndPasswordAndActive(userName,password,true);
	}


	public boolean changePassword(String userName, String oldPassword, String newPassword) {
		User emp= repository.findByUserNameAndPasswordAndActive(userName,oldPassword,true);

		if (emp!=null && emp.getEmployeeId()!=null) {
			emp.setPassword(newPassword);
			emp.setUpdatedDate(LocalDate.now());
			emp.setUpdatedBy(emp.getEmployeeId());
			repository.save(emp);
			return true;
		}

		return false;
	}

	public boolean checkUserNameExists(String userName) {
		User emp= repository.findByUserNameAndActive(userName,true);
		return emp!=null && emp.getEmployeeId()!=null ? true:false;
	}
	
	public User findByUserNameAndActive(String userName) {
		User emp= repository.findByUserNameAndActive(userName,true);
		return emp;
	}


	public boolean existsByUserName(String userName) {
		return repository.existsByUserName(userName);
	}
	
	
}

