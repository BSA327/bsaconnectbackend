package com.company.bsaadmin.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.bsaadmin.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByActive(boolean active);

	User findByUserNameAndPasswordAndActive(String userName, String password,boolean active);

	User findByUserNameAndActive(String userName, boolean active);
	
	boolean existsByUserName(String userName);
	
	User findByIdAndActive(Long id, boolean active);

}

