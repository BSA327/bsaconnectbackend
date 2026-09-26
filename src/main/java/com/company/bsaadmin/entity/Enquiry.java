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
public class Enquiry extends Base {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="customer_id",insertable = false, updatable = false)
    @JsonIgnore
    private Customer customer;
    
    @Column(name="customer_id")
	private Long customerId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(length=3000)
    private String details;

    public enum Status { OPEN, IN_PROCESS, SUCCESS, FAILED }
    
    private String location;

	private String timeline;

	private String budget;

	private String financeAssistance;

	@JsonProperty("customerName")
	public String getCustomerName() {
	    return customer != null && customer.getName() != null
	            ? customer.getName()
	            : null;
	}

}
