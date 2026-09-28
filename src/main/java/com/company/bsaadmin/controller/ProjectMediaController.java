package com.company.bsaadmin.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.company.bsaadmin.entity.ProjectMedia;
import com.company.bsaadmin.service.ProjectMediaService;

@CrossOrigin
@RestController
@RequestMapping("/api/projectmedia")
public class ProjectMediaController {

	@Autowired
	private ProjectMediaService service;

	@Value("${upload.projectdirectory}")
	private String uploadDir;

	@Value("${server.servlet.context-path}")
	private String contextPath;

	@Autowired
	private HttpServletRequest request;

	@GetMapping("/{projectId}/media")
	public List<ProjectMedia> getProjectMedia(@PathVariable Long projectId) {
		return service.findByProjectId(projectId);
	}

	/**
	 * Upload project image/video
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@PostMapping(value = "/{projectId}/media",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> uploadMedia(@PathVariable Long projectId, @RequestParam("file") MultipartFile[]  files) throws IllegalStateException, IOException {

		if (files.length <= 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
		}

		// Define the directory where files will be stored
		String uploadDirectory = System.getProperty("user.home") + uploadDir + projectId + "/";

		// Create the directory if it doesn't exist
		File directory = new File(uploadDirectory);
		if (!directory.exists()) {
			if (!directory.mkdirs()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create directories");
			}
		}

		for (MultipartFile file : files) {
			// Generate a unique file name to avoid overwriting files
			String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

			// Define the file path
			String filePath = uploadDirectory + uniqueFileName;

			// Save the file to the server
			File serverFile = new File(filePath);
			file.transferTo(serverFile);

			String serverAddress = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
			String fileUrl = serverAddress + contextPath + uploadDir + projectId + "/" + uniqueFileName;


			ProjectMedia media =new ProjectMedia();
			media.setProjectId(projectId);
			media.setFileName(file.getOriginalFilename());
			media.setFilePath(fileUrl);
			media.setFileType(file.getContentType());
			if (file.getContentType() != null &&
					file.getContentType().startsWith("video/")) {

				media.setMediaType(ProjectMedia.MediaType.VIDEO);

			} else {

				media.setMediaType(ProjectMedia.MediaType.IMAGE);
			}
			media.setCover(false);
			service.save(media);
		}

		return ResponseEntity.ok().body("File uploaded successfully");
	}
}
