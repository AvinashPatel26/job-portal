package com.jobportal.controller;

import com.jobportal.dto.DashboardAnalytics;
import com.jobportal.dto.DashboardStats;
import com.jobportal.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/stats")
    public DashboardStats getStats(
            @RequestParam Long userId) {
        return dashboardService.getStats(userId);
    }

    @GetMapping("/analytics")
    public DashboardAnalytics getAnalytics(
            @RequestParam Long userId) {
        return dashboardService.getAnalytics(userId);
    }
}
