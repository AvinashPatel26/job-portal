package com.jobportal.controller;

import com.jobportal.model.Job;
import com.jobportal.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
public class JobController {

    private final JobService jobService;

    @PostMapping
    public Job postJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }

    @GetMapping
    public List<Job> getJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/search")
    public List<Job> search(@RequestParam String location) {
        return jobService.searchByLocation(location);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        jobService.deleteJob(id);
    }
    @GetMapping("/recent")
    public List<Job> getRecentJobs() {
        return jobService.getRecentJobs();
    }

}
