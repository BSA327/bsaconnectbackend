package com.company.bsaadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.bsaadmin.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    
}
