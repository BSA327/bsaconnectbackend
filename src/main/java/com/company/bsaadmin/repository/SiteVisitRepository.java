package com.company.bsaadmin.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.bsaadmin.entity.SiteVisit;

@Repository
public interface SiteVisitRepository extends JpaRepository<SiteVisit, Long> {
    List<SiteVisit> findByBdmIdAndVisitDateBetween(Long bdmId, LocalDate from, LocalDate to); 
    List<SiteVisit> findByInventoryId(Long inventoryId);
}
