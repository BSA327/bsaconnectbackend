package com.company.bsaadmin.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
                         @RequestParam(required=false) LocalDate toDate,
                         Authentication auth) {
        User u = users.findByUserNameAndActive(auth.getName());
        LocalDate from = fromDate != null ? fromDate : LocalDate.now().withDayOfMonth(1);
        LocalDate to = toDate != null ? toDate : LocalDate.now().withDayOfMonth(1).plusMonths(1).minusDays(1);
        return repo.findByUserIdAndDateBetween(u.getId(), from, to);
    }

    @PostMapping
    public Task create(@RequestBody Task task, Authentication auth) {
        User u = users.findByUserNameAndActive(auth.getName());
        task.setId(null);
        task.setUser(u);
        //if (task.getStatus() == null) task.setStatus(Task.Status.PENDING);
        task.setActive(true);
        task.setCreatedDate(LocalDate.now());
        task.setCreatedBy(u.getId());
        return repo.save(task);
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Task> search(@RequestParam Long employeeId,
                             @RequestParam LocalDate fromDate,
                             @RequestParam LocalDate toDate) {
        return repo.findByUserIdAndDateBetween(employeeId, fromDate, toDate);
    }
}