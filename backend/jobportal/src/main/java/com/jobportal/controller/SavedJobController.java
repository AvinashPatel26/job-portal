package com.jobportal.controller;

import com.jobportal.model.SavedJob;
import com.jobportal.service.SavedJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/saved-jobs")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
public class SavedJobController {

    private final SavedJobService service;

    @PostMapping
    public void saveJob(
            @RequestParam Long userId,
            @RequestParam Long jobId) {

        service.saveJob(userId, jobId);
    }

    @DeleteMapping
    public void unsaveJob(
            @RequestParam Long userId,
            @RequestParam Long jobId) {

        service.unsaveJob(userId, jobId);
    }

    @GetMapping
    public List<SavedJob> getSavedJobs(
            @RequestParam Long userId) {

        return service.getSavedJobs(userId);
    }
}
