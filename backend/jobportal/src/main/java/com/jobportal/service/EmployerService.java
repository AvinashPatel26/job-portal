package com.jobportal.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jobportal.model.Job;
import com.jobportal.model.JobApplication;
import com.jobportal.model.User;
import com.jobportal.repository.JobApplicationRepository;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployerService {

    private final JobApplicationRepository jobApplicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    // ==============================
    // JOB MANAGEMENT
    // ==============================

    public Job createJob(Job job, Long employerId) {

        User employer = userRepository.findById(employerId)
                .orElseThrow(() -> new RuntimeException("Employer not found"));

        job.setPostedBy(employer);

        return jobRepository.save(job);
    }

    public List<Job> getEmployerJobs(Long employerId) {
        return jobRepository.findByPostedBy_Id(employerId);
    }

    // ==============================
    // EMPLOYER ANALYTICS
    // ==============================

    public List<Map<String, Object>> getEmployerAnalytics(Long employerId) {
        return jobApplicationRepository
                .countEmployerApplicationsByStatus(employerId);
    }

    // ==============================
    // APPLICATION PIPELINE (ATS)
    // ==============================

    public List<JobApplication> getApplicationsByJob(Long jobId) {
        return jobApplicationRepository.findByJob_Id(jobId);
    }

    public void updateApplicationStatus(Long applicationId, String status) {

        JobApplication application =
                jobApplicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new RuntimeException("Application not found"));

        application.setStatus(status);
        jobApplicationRepository.save(application);
    }
    public Map<String, List<JobApplication>> getApplicationPipeline(Long jobId) {

        List<JobApplication> apps =
                jobApplicationRepository.findByJob_Id(jobId);

        return apps.stream()
                .collect(Collectors.groupingBy(JobApplication::getStatus));
    }

}
