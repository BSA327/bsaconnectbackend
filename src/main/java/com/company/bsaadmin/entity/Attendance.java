package com.company.bsaadmin.entity;

import java.time.LocalDate;
import java.time.LocalTime;

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
public class Attendance extends Base {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="user_id", nullable=false)
	@JsonIgnore
	private User user;

	@Column(name="attendance_date", nullable=false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate attendanceDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime loginTime;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime logoutTime;

	private Double loginLatitude;
	private Double loginLongitude;
	private Double logoutLatitude;
	private Double logoutLongitude;

	private String loginIp;
	private String logoutIp;

	private String loginLocation;
	private String logoutLocation;

	@Column(length=500)
	private String remarks;

	@Enumerated(EnumType.STRING)
	private Status status;

	public enum Status { PRESENT, PARTIAL, COMPLETED }
	
	
	@JsonProperty("employeeName")
	public String getEmployeeName() {
	    return user != null && user.getEmployee() != null
	            ? user.getEmployee().getName()
	            : null;
	}



}
