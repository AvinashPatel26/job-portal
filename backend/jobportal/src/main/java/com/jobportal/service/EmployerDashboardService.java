package com.jobportal.service;

import org.springframework.stereotype.Service;

import com.jobportal.dto.EmployerDashboardStats;
import com.jobportal.repository.JobApplicationRepository;
import com.jobportal.repository.JobRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployerDashboardService {

    private final JobRepository jobRepository;
    private final JobApplicationRepository applicationRepository;

    public EmployerDashboardStats getStats(Long employerId) {

        Long totalJobs =
                jobRepository.countByPostedBy_Id(employerId);

        Long totalApplications =
                applicationRepository.countByEmployerJobs(employerId);

        Long shortlisted =
                applicationRepository.countByEmployerAndStatus(
                        employerId, "SHORTLISTED");

        Long rejected =
                applicationRepository.countByEmployerAndStatus(
                        employerId, "REJECTED");

        return new EmployerDashboardStats(
                totalJobs,
                totalApplications,
                shortlisted,
                rejected
        );
    }
}
