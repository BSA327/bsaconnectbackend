package com.company.bsaadmin.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.bsaadmin.dto.request.LoginRequest;
import com.company.bsaadmin.entity.User;
import com.company.bsaadmin.service.EmployeeService;
import com.company.bsaadmin.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class LoginController {

	@Value("${app.jwtSecret}")
	private String jwtSecret;

	@Value("${app.jwtExpirationInMs}")
	private int jwtExpirationInMs;

	@Autowired
	private ApplicationContext appContext;

	@Autowired
	private UserService userService;

	@Autowired
	private EmployeeService empService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/authenticate")
	public ResponseEntity<String> authenticateAndGetToken(@RequestBody LoginRequest loginuser,
			HttpServletRequest request) {

		User user = userService.findByUserNameAndActive(loginuser.getUsername());
		if (user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Login Name");
		}

		boolean passwordMatch = passwordEncoder.matches(loginuser.getPassword(), user.getPassword());

		if (!passwordMatch) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Password");
		}

		if (user != null && user.getId() != null) {
			String ipAddress = request.getHeader("X-Forwarded-For");

			if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getRemoteAddr();
			}

			userService.save(user);

			Date now = new Date();
			Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

			String token = Jwts.builder().setIssuedAt(new Date()).setExpiration(expiryDate)
					.claim("username", user.getUserName()).claim("userId", user.getId())
					.claim("employeeId", user.getEmployeeId()).claim("role", user.getRole())
					.claim("loginName", user.getEmployee().getName())
					.setIssuer(appContext.getApplicationName().replace("/", ""))
					.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
			return ResponseEntity.ok(token);
		} else {
			return ResponseEntity.status(401).body("Invalid credentials");
		}

	}
}
