package com.company.bsaadmin.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.bsaadmin.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    
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
