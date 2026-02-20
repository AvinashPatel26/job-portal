package com.jobportal.service;

import com.jobportal.dto.AdminDashboardStats;
import com.jobportal.model.Job;
import com.jobportal.model.JobApplication;
import com.jobportal.model.User;
import com.jobportal.repository.JobApplicationRepository;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final JobApplicationRepository applicationRepository;

    // =========================
    // DASHBOARD STATS
    // =========================
    public AdminDashboardStats getStats() {

        Long users = userRepository.countByRole("USER");
        Long employers = userRepository.countByRole("EMPLOYER");
        Long jobs = jobRepository.count();
        Long applications = applicationRepository.count();

        return new AdminDashboardStats(
                users,
                employers,
                jobs,
                applications
        );
    }

    // =========================
    // USERS
    // =========================
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void toggleUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setActive(!user.isActive());
        userRepository.save(user);
    }

    // =========================
    // JOBS
    // =========================
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public void deleteJob(Long jobId) {
        jobRepository.deleteById(jobId);
    }

    // =========================
    // APPLICATIONS
    // =========================
    public List<JobApplication> getAllApplications() {
        return applicationRepository.findAll();
    }
}
