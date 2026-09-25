package com.company.bsaadmin.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.bsaadmin.entity.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByUserIdAndAttendanceDateBetween(Long userId, LocalDate from, LocalDate to); 
    Optional<Attendance> findByUserIdAndAttendanceDate(Long userId, LocalDate date);
}
