package com.jobportal.service;

import com.jobportal.dto.DashboardAnalytics;
import com.jobportal.dto.DashboardStats;
import com.jobportal.repository.JobApplicationRepository;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.NotificationRepository;
import com.jobportal.repository.SavedJobRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final JobApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final SavedJobRepository savedJobRepository;
    private final NotificationRepository notificationRepository;



    public DashboardStats getStats(Long userId) {

        Long appliedJobs =
                applicationRepository.countByApplicant_Id(userId);

        Long availableJobs =
                jobRepository.count();

        Long savedJobs =
                savedJobRepository.countByUser_Id(userId);
        Long unreadNotifications =
                notificationRepository.countByUserIdAndReadStatusFalse(userId);



        return new DashboardStats(
                appliedJobs,
                savedJobs,
                availableJobs,unreadNotifications
        );
    }

    public DashboardAnalytics getAnalytics(Long userId) {

        List<Map<String, Object>> monthly =
                applicationRepository.countApplicationsPerMonth(userId);

        List<Map<String, Object>> status =
                applicationRepository.countByStatus(userId);

        return new DashboardAnalytics(monthly, status);
    }
}
