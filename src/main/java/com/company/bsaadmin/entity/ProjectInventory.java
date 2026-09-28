package com.company.bsaadmin.entity;

import java.math.BigDecimal;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor 
@AllArgsConstructor 
public class ProjectInventory extends Base  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_ID",insertable = false, updatable =false)
	@JsonIgnore
	private Project project;
	
	@Column(name="PROJECT_ID")
	private Long projectId;

	private String unitNo;

	@Enumerated(EnumType.STRING)
	private UnitType unitType;
	
	public enum UnitType { PLOTS, VILLA, APRATMENT, COMMERCIAL  }

	private String floor;

	private Double area;

	private String facing;

	private BigDecimal price;

	@Enumerated(EnumType.STRING)
	private Status status;
	
	public enum Status { AVAILABLE, RESERVED, SOLD }

	private String remarks;
}
