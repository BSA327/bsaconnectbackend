package com.company.bsaadmin.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.bsaadmin.entity.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    
	long countByActiveTrueAndCreatedDateBetween(
	        LocalDate fromDate,
	        LocalDate toDate
	);

	long countByActiveTrueAndCreatedByAndCreatedDateBetween(
	        Long createdBy,
	        LocalDate fromDate,
	        LocalDate toDate
	);
}
