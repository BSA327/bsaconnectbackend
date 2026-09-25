package com.company.bsaadmin.entity;

import java.time.LocalDate;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Base {

	private boolean active;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate createdDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate updatedDate;

	private Long createdBy;

	private Long updatedBy;
}
