package com.company.bsaadmin.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.bsaadmin.entity.Task;
import com.company.bsaadmin.entity.User;
import com.company.bsaadmin.service.TaskService;
import com.company.bsaadmin.service.UserService;
import com.company.bsaadmin.util.SecurityUtils;
@CrossOrigin
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	@Autowired
    private TaskService repo;
	
	@Autowired
    private  UserService users;

    @GetMapping("/my")
    public List<Task> my(@RequestParam(required=false) LocalDate fromDate,
                         @RequestParam(required=false) LocalDate toDate) {
        User u = users.findByUserNameAndActive(SecurityUtils.getUsername());
        LocalDate from = fromDate != null ? fromDate : LocalDate.now().withDayOfMonth(1);
        LocalDate to = toDate != null ? toDate : LocalDate.now().withDayOfMonth(1).plusMonths(1).minusDays(1);
        return repo.findByUserIdAndDateBetween(u.getId(), from, to);
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        User u = users.findByUserNameAndActive(SecurityUtils.getUsername());
        task.setId(null);
        task.setUser(u);
        return repo.save(task);
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Task> search(@RequestParam(required = false) Long employeeId,

			@RequestParam(required = false)
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
			LocalDate fromDate,

			@RequestParam(required = false)
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
			LocalDate toDate) {
        return repo.findByUserIdAndDateBetween(employeeId, fromDate, toDate);
    }
}