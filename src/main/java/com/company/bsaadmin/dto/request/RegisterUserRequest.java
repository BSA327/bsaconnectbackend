package com.company.bsaadmin.dto.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequest {

	
	
	private String name;
	private Long gender;
	private String email;
	private String phone;
	private String communicationAddress;
	private String username;
	private String aadharNumber;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate joiningDate;
	
	private String role;
	private boolean active;
	private String password;
}
