package com.jobportal.controller;

import com.jobportal.model.Job;
import com.jobportal.service.EmployerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employer")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
public class EmployerController {

    private final EmployerService employerService;

    @PostMapping("/jobs")
    public Job createJob(
            @RequestBody Job job,
            @RequestParam Long employerId) {

        return employerService.createJob(job, employerId);
    }

    @GetMapping("/jobs")
    public List<Job> getEmployerJobs(
            @RequestParam Long employerId) {

        return employerService.getEmployerJobs(employerId);
    }
}
