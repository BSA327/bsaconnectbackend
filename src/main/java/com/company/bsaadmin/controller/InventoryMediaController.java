package com.company.bsaadmin.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.company.bsaadmin.entity.Inventory;
import com.company.bsaadmin.entity.InventoryMedia;
import com.company.bsaadmin.service.InventoryMediaService;
import com.company.bsaadmin.service.InventoryService;

@CrossOrigin
@RestController
@RequestMapping("/api/inventory")
public class InventoryMediaController {
	
	@Autowired
    private InventoryService inventoryRepo;
	
	@Autowired
    private InventoryMediaService mediaRepo;

    @Value("${app.upload-dir:./uploads}")
    private String uploadDir;

   
    @PostMapping("/{id}/media")
    public List<InventoryMedia> upload(@PathVariable Long id,
                                       @RequestParam("files") MultipartFile[] files) throws Exception {
        Inventory inventory = inventoryRepo.findById(id);
        Path dir = Paths.get(uploadDir, "inventory", String.valueOf(id));
        Files.createDirectories(dir);

        List<InventoryMedia> result = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;

            String clean = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            String stored = UUID.randomUUID() + "_" + clean;
            Files.copy(file.getInputStream(), dir.resolve(stored), StandardCopyOption.REPLACE_EXISTING);

            InventoryMedia.MediaType type =
                file.getContentType() != null && file.getContentType().startsWith("video/")
                ? InventoryMedia.MediaType.VIDEO : InventoryMedia.MediaType.IMAGE;

            InventoryMedia media = InventoryMedia.builder()
                .inventory(inventory)
                .fileName(clean)
                .fileUrl("/uploads/inventory/" + id + "/" + stored)
                .contentType(file.getContentType())
                .mediaType(type)
                .build();

            result.add(mediaRepo.save(media));
        }

        return result;
    }

    @GetMapping("/{id}/media")
    public List<InventoryMedia> list(@PathVariable Long id) {
        return mediaRepo.findByInventoryId(id);
    }
}