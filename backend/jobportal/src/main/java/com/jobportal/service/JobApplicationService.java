package com.jobportal.service;

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
public class JobApplicationService {

    private final JobApplicationRepository repository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public void applyJob(Long userId, Long jobId) {

        if (repository.existsByApplicant_IdAndJob_Id(userId, jobId)) {
            throw new RuntimeException("Already applied");
        }

        Job job = jobRepository.findById(jobId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

        JobApplication app = JobApplication.builder()
                .job(job)
                .applicant(user)
                .status("APPLIED")
                .build();

        repository.save(app);
    }

    public List<JobApplication> getUserApplications(Long userId) {
        return repository.findByApplicant_Id(userId);
    }
}
