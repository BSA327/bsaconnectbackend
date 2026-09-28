package com.company.bsaadmin.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class Project extends Base  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String projectName;

    @Enumerated(EnumType.STRING)
	private ProjectType projectType;

	public enum ProjectType { PLOTS, VILLA, APRATMENT, COMMERCIAL  }

	private String location;
	
	private Double latitude;
	
	private Double longitude;

    private Double totalUnits;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate completionDate;

    @Enumerated(EnumType.STRING)
    private Status status;
    
    public enum Status {AVAILABLE, RESERVED, SOLD}


}