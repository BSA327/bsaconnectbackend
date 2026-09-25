package com.company.bsaadmin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor 
@AllArgsConstructor 
public class User extends Base {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long employeeId;

	@ManyToOne(fetch = FetchType.LAZY, optional = true) 
	@JoinColumn(name = "employeeId", referencedColumnName = "id", insertable = false, updatable = false)
	@NotFound(action=NotFoundAction.IGNORE)
	private Employee employee;


	private String userName;

	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Role role;

	public enum Role { ADMIN, CRM, BDM }



}
