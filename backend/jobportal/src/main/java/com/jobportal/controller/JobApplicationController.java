package com.jobportal.controller;

import com.jobportal.model.JobApplication;
import com.jobportal.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
public class JobApplicationController {

    private final JobApplicationService service;

    @PostMapping("/apply")
    public String applyJob(
            @RequestParam Long userId,
            @RequestParam Long jobId) {

        service.applyJob(userId, jobId);
        return "Applied successfully";
    }

    @GetMapping("/my")
    public List<JobApplication> myApplications(
            @RequestParam Long userId) {

        return service.getUserApplications(userId);
    }
}
