package com.company.bsaadmin.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.bsaadmin.dto.request.AttendanceRequest;
import com.company.bsaadmin.entity.Attendance;
import com.company.bsaadmin.entity.User;
import com.company.bsaadmin.service.AttendanceService;
import com.company.bsaadmin.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

	@Autowired
	private AttendanceService service;

	@Autowired
	private UserService users;

	@PostMapping("/check-in")
	public ResponseEntity<?> checkIn(
			@RequestBody(required=false) AttendanceRequest request,
			HttpServletRequest http,
			Authentication auth
			) {
		User user =users.findByUserNameAndActive(auth.getName());
		LocalDate today = LocalDate.now();
		Attendance a = service.findByUserIdAndAttendanceDate(user.getId(), today)
				.orElse(Attendance.builder().user(user).attendanceDate(today).build());
        if(a.getId()!=null) {
        	 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Checkin Already done");
        }
		a.setLoginTime(LocalTime.now());
		a.setLoginLatitude(request == null ? null : request.getLatitude());
		a.setLoginLongitude(request == null ? null : request.getLongitude());
		a.setLoginLocation(request == null ? null : request.getLocation());
		//a.setLoginIp(clientIp(http));
		a.setStatus(Attendance.Status.PRESENT);

		return ResponseEntity.ok().body(a);
	}

	@PostMapping("/check-out")
	public ResponseEntity<?> checkOut(
			@RequestBody(required=false) AttendanceRequest request,
			HttpServletRequest http,
			Authentication auth
			) {
		User user = users.findByUserNameAndActive(auth.getName());
		Attendance a = service.findByUserIdAndAttendanceDate(user.getId(), LocalDate.now())
				.orElseThrow(() -> new IllegalStateException("Please check in first."));
		if(a.getId()!=null && a.getLoginTime()!=null) {
       	 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Checkout Already done");
       }
		a.setLogoutTime(LocalTime.now());
		a.setLogoutLatitude(request == null ? null : request.getLatitude());
		a.setLogoutLongitude(request == null ? null : request.getLongitude());
		a.setLogoutLocation(request == null ? null : request.getLocation());
		//a.setLogoutIp(clientIp(http));
		a.setStatus(Attendance.Status.COMPLETED);

		return ResponseEntity.ok().body(a);
	}

	@GetMapping("/my")
	public List<Attendance> my(
			@RequestParam String month,
			Authentication auth
			) {
		User user =users.findByUserNameAndActive(auth.getName());
		YearMonth ym = YearMonth.parse(month);
		return service.findByUserIdAndAttendanceDateBetween(
				user.getId(), ym.atDay(1), ym.atEndOfMonth()
				);
	}

	@GetMapping("/search")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Attendance> search(
			@RequestParam Long employeeId,
			@RequestParam LocalDate fromDate,
			@RequestParam LocalDate toDate
			) {
		return service.findByUserIdAndAttendanceDateBetween(employeeId, fromDate, toDate);
	}

}