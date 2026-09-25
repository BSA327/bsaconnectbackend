package com.company.bsaadmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.bsaadmin.entity.InventoryMedia;

@Repository
public interface InventoryMediaRepository extends JpaRepository<InventoryMedia, Long> {
    List<InventoryMedia> findByInventoryId(Long inventoryId);
}
