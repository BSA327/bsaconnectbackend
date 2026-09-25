package com.company.bsaadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.bsaadmin.entity.Enquiry;

@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {
    
}
