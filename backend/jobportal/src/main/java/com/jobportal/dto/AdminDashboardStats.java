package com.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminDashboardStats {

    private Long totalUsers;
    private Long totalEmployers;
    private Long totalJobs;
    private Long totalApplications;
}
