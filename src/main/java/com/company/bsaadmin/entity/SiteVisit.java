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
public class SiteVisit extends Base {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="inventory_id", nullable=false)
    private Inventory inventory;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="enquiry_id")
    private Enquiry enquiry;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name="visit_date", nullable=false)
    private LocalDate visitDate;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Column(name="visit_time", nullable=false)
    private LocalDate visitTime;

    @Enumerated(EnumType.STRING)
    private Status status = Status.SCHEDULED;

    @Column(length=2000)
    private String remarks;

    public enum Status { SCHEDULED, COMPLETED, CANCELLED }



}
