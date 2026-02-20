package com.jobportal.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.model.Job;
import com.jobportal.model.JobApplication;
import com.jobportal.service.EmployerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employer")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
public class EmployerController {

    private final EmployerService employerService;

    // =====================================================
    // JOB MANAGEMENT
    // =====================================================

    // Create Job
    @PostMapping("/jobs")
    public Job createJob(
            @RequestBody Job job,
            @RequestParam Long employerId) {

        return employerService.createJob(job, employerId);
    }

    // Employer Jobs List
    @GetMapping("/jobs")
    public List<Job> getEmployerJobs(
            @RequestParam Long employerId) {

        return employerService.getEmployerJobs(employerId);
    }


    // =====================================================
    // ATS / HIRING PIPELINE
    // =====================================================

    // Get all applications for a job
    @GetMapping("/jobs/{jobId}/applications")
    public List<JobApplication> getApplicationsByJob(
            @PathVariable Long jobId) {

        return employerService.getApplicationsByJob(jobId);
    }

    // Update application status
    @PutMapping("/applications/{applicationId}/status")
    public void updateApplicationStatus(
            @PathVariable Long applicationId,
            @RequestParam String status) {

        employerService.updateApplicationStatus(applicationId, status);
    }
    @GetMapping("/pipeline")
    public Map<String, List<JobApplication>> getPipeline(
            @RequestParam Long jobId) {

        return employerService.getApplicationPipeline(jobId);
    }

}
