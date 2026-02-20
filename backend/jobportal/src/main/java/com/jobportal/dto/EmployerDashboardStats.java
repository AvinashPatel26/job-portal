package com.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployerDashboardStats {

    private Long totalJobs;
    private Long totalApplications;
    private Long shortlisted;
    private Long rejected;
}
