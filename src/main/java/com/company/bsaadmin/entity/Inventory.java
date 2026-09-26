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
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	private Double latitude;
	private Double longitude;

	@Enumerated(EnumType.STRING)
	private Type type;

	public enum Type { NA_LAND, AGRICULTURE_LAND, INDEPENDENT_HOUSE, APRATMENT, COMMERCIAL_PROPERTY  }

	private Double size;
	private BigDecimal price;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Column(length=4000)
	private String description;

	public enum Status { AVAILABLE, RESERVED, SOLD, INACTIVE }

	private String remarks;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="customer_id",insertable = false, updatable =false)
	@JsonIgnore
	private Customer customer;
	
	@Column(name="customer_id")
	private Long customerId;

	@JsonProperty("customerName")
	public String getCustomerName() {
	    return customer != null && customer.getName() != null
	            ? customer.getName()
	            : null;
	}

}
