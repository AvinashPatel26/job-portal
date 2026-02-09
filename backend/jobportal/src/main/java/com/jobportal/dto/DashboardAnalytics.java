package com.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class DashboardAnalytics {

    private List<Map<String, Object>> applicationsPerMonth;
    private List<Map<String, Object>> statusBreakdown;
}
