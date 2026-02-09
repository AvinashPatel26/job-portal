package com.jobportal.controller;

import com.jobportal.dto.*;
import com.jobportal.security.CustomUserDetails;
import com.jobportal.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/stats")
    public DashboardStats stats(@RequestParam Long userId) {
        return dashboardService.getStats(userId);
    }

    @GetMapping("/analytics")
    public DashboardAnalytics analytics(@RequestParam Long userId) {
        return dashboardService.getAnalytics(userId);
    }
}
