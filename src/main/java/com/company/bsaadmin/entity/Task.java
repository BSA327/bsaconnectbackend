package com.company.bsaadmin.entity;

import java.time.LocalDate;

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

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor 
@AllArgsConstructor 
public class Task extends Base {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="user_id", nullable=false)
	private User user;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(nullable=false)
	private LocalDate date;

	@Column(nullable=false, length=2000)
	private String details;

	@Enumerated(EnumType.STRING)
	private Status status = Status.PENDING;

	public enum Status { PENDING, IN_PROGRESS, COMPLETED, CANCELLED }



}
