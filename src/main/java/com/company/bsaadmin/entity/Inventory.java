package com.company.bsaadmin.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor 
@AllArgsConstructor 
public class Inventory extends Base {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false)
	private String title;

	private String location;
	private String type;
	private String area;
	private BigDecimal price;

	@Enumerated(EnumType.STRING)
	private Status status = Status.AVAILABLE;

	@Column(length=4000)
	private String description;

	@OneToMany(mappedBy="inventory", cascade=CascadeType.ALL, orphanRemoval=true)
	@Builder.Default
	private List<InventoryMedia> media = new ArrayList<>();

	public enum Status { AVAILABLE, RESERVED, SOLD, INACTIVE }



}
