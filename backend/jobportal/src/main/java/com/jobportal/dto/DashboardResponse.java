package com.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {

    private Long appliedJobs;
    private Long savedJobs;
    private Long availableJobs;

    private List<MonthlyData> applicationsPerMonth;
    private List<StatusData> statusBreakdown;
}
