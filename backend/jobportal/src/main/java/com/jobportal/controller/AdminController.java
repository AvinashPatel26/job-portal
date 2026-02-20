package com.jobportal.controller;

import com.jobportal.dto.AdminDashboardStats;
import com.jobportal.model.Job;
import com.jobportal.model.JobApplication;
import com.jobportal.model.User;
import com.jobportal.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/stats")
    public AdminDashboardStats stats() {
        return adminService.getStats();
    }

    @GetMapping("/users")
    public List<User> users() {
        return adminService.getAllUsers();
    }

    @PutMapping("/users/{id}/toggle")
    public void toggleUser(@PathVariable Long id) {
        adminService.toggleUser(id);
    }

    @GetMapping("/jobs")
    public List<Job> jobs() {
        return adminService.getAllJobs();
    }

    @DeleteMapping("/jobs/{id}")
    public void deleteJob(@PathVariable Long id) {
        adminService.deleteJob(id);
    }

    @GetMapping("/applications")
    public List<JobApplication> applications() {
        return adminService.getAllApplications();
    }
}
