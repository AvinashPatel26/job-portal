package com.jobportal.controller;

import com.jobportal.model.JobApplication;
import com.jobportal.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employer/applications")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
public class EmployerApplicationController {

    private final JobApplicationService service;

    // Get all applications for a job
    @GetMapping
    public List<JobApplication> getByJob(
            @RequestParam Long jobId) {

        return service.getApplicationsByJob(jobId);
    }

    // Move application in pipeline
    @PutMapping("/{id}/move")
    public String moveApplication(
            @PathVariable Long id,
            @RequestParam String status) {

        service.updateStatus(id, status);
        return "Status updated";
    }
}
