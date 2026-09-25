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
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="inventory_id")
    private Inventory inventory;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Status status = Status.OPEN;

    @Column(length=3000)
    private String details;

    public enum Status { OPEN, FOLLOW_UP, CONVERTED, LOST, CLOSED }



}
