package com.company.bsaadmin.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.bsaadmin.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserIdAndDateBetween(Long userId, LocalDate from, LocalDate to);
}
