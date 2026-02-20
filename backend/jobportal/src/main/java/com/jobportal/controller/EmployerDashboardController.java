package com.jobportal.controller;

import com.jobportal.dto.EmployerDashboardStats;
import com.jobportal.service.EmployerDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employer/dashboard")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
public class EmployerDashboardController {

    private final EmployerDashboardService service;

    @GetMapping("/stats")
    public EmployerDashboardStats getStats(
            @RequestParam Long employerId) {
        return service.getStats(employerId);
    }
}
