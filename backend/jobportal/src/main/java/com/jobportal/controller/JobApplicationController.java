package com.jobportal.controller;

import com.jobportal.model.JobApplication;
import com.jobportal.service.JobApplicationService;
import lombok.RequiredArgsConstructor;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
public class JobApplicationController {

    private final JobApplicationService service;

    // ✅ Apply job
    @PostMapping("/apply")
    public String applyJob(
            @RequestParam Long userId,
            @RequestParam Long jobId) {

        service.applyJob(userId, jobId);
        return "Applied successfully";
    }

    // ✅ My applications
    @GetMapping("/my")
    public List<JobApplication> myApplications(
            @RequestParam Long userId) {

        return service.getUserApplications(userId);
    }

    // ✅ Employer updates status
    @PutMapping("/{id}/status")
    public void updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        service.updateStatus(id, status);
    }

    // ✅ Upload resume
    @PostMapping("/{id}/resume")
    public String uploadResume(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {

        service.uploadResume(id, file);
        return "Resume uploaded";
    }

    // ✅ Download resume
    @GetMapping("/resume/{fileName}")
    public ResponseEntity<Resource> downloadResume(
            @PathVariable String fileName) throws IOException {

        Path path = Paths.get("uploads/resumes/" + fileName);

        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }
    @PutMapping("/{id}/notes")
    public void addNotes(
            @PathVariable Long id,
            @RequestParam String notes) {

        service.addRecruiterNotes(id, notes);
    }

    @PutMapping("/{id}/interview")
    public void scheduleInterview(
            @PathVariable Long id,
            @RequestParam String dateTime) {

        service.scheduleInterview(
                id,
                java.time.LocalDateTime.parse(dateTime)
        );
    }

}
