package com.company.bsaadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.bsaadmin.entity.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    
}
