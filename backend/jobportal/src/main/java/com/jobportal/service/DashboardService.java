package com.jobportal.service;

import com.jobportal.dto.DashboardAnalytics;
import com.jobportal.dto.DashboardStats;
import com.jobportal.repository.JobApplicationRepository;
import com.jobportal.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final JobApplicationRepository applicationRepository;
    private final JobRepository jobRepository;

    public DashboardStats getStats(Long userId) {

        Long appliedJobs =
                applicationRepository.countByApplicant_Id(userId);

        Long availableJobs =
                jobRepository.count();

        Long savedJobs = 0L;

        return new DashboardStats(
                appliedJobs,
                savedJobs,
                availableJobs
        );
    }

    public DashboardAnalytics getAnalytics(Long userId) {

        return new DashboardAnalytics(
                applicationRepository.countApplicationsPerMonth(userId),
                applicationRepository.countByStatus(userId)
        );
    }
}
