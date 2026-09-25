package com.company.bsaadmin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor 
@AllArgsConstructor 
public class Agent extends Base {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false)
	private String name;

	private String phone;
	private String email;
	private String city;

	@Enumerated(EnumType.STRING)
	private Status status = Status.ACTIVE;

	@Column(length=2000)
	private String remarks;

	public enum Status { ACTIVE, INACTIVE }



}
