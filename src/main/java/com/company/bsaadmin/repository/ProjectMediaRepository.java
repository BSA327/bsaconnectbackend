package com.company.bsaadmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.bsaadmin.entity.ProjectMedia;

@Repository
public interface ProjectMediaRepository extends JpaRepository<ProjectMedia, Long> {

	List<ProjectMedia> findByProjectId(Long projectId);
    
}
