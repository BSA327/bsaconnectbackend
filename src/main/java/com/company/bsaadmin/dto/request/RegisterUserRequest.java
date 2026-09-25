package com.company.bsaadmin.dto.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequest {

	private String userName;
	private String password;
	private String role;
	private Long createdBy;

	private String name;
	private Long gender;
	private String mobileNumber;
	private String emailId;
	private String communicationAddress;
	private String permanentAddress;
	private String city;
	private String state;
	private String country;
	private String aadharNumber;
	private String panNumber;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate joiningDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate exitDate;
	
}
