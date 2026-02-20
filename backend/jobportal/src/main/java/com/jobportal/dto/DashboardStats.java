package com.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardStats {
    private Long appliedJobs;
    private Long savedJobs;
    private Long availableJobs;
    private Long unreadNotifications;

}
