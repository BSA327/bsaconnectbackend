package com.company.bsaadmin.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.company.bsaadmin.entity.Attendance;
import com.company.bsaadmin.repository.AttendanceRepository;

@Service
public class AttendanceService  extends GenericService<Attendance, Long>  {

	private final AttendanceRepository repository;

	public AttendanceService(AttendanceRepository repository) {
		super(repository);
	    this.repository = repository;
	}

	public List<Attendance> findByUserIdAndAttendanceDateBetween(Long userId, LocalDate from, LocalDate to) {
		return repository.findByUserIdAndAttendanceDateBetween( userId,  from,  to);
	}


	public Optional<Attendance> findByUserIdAndAttendanceDate(Long userId, LocalDate date) {
		return repository. findByUserIdAndAttendanceDate( userId,  date);
	}


}

