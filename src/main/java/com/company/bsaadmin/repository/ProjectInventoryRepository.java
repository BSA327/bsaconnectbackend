package com.company.bsaadmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.bsaadmin.entity.ProjectInventory;

@Repository
public interface ProjectInventoryRepository extends JpaRepository<ProjectInventory, Long> {

	List<ProjectInventory> findByProjectId(Long projectId);
    
}
