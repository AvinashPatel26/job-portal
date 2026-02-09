package com.jobportal.service;

import com.jobportal.model.Job;
import com.jobportal.model.User;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployerService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public Job createJob(Job job, Long employerId) {

        User employer = userRepository.findById(employerId)
                .orElseThrow();

        job.setPostedBy(employer);

        return jobRepository.save(job);
    }

    public List<Job> getEmployerJobs(Long employerId) {
        return jobRepository.findByPostedBy_Id(employerId);
    }
}
