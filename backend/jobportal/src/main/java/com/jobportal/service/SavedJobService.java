package com.jobportal.service;

import com.jobportal.model.Job;
import com.jobportal.model.SavedJob;
import com.jobportal.model.User;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.SavedJobRepository;
import com.jobportal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SavedJobService {

    private final SavedJobRepository repository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public void saveJob(Long userId, Long jobId) {

        if (repository.existsByUser_IdAndJob_Id(userId, jobId))
            return;

        User user = userRepository.findById(userId).orElseThrow();
        Job job = jobRepository.findById(jobId).orElseThrow();

        repository.save(
                SavedJob.builder()
                        .user(user)
                        .job(job)
                        .build()
        );
    }

    public void unsaveJob(Long userId, Long jobId) {
        repository.deleteByUser_IdAndJob_Id(userId, jobId);
    }

    public List<SavedJob> getSavedJobs(Long userId) {
        return repository.findByUser_Id(userId);
    }

    public Long countSavedJobs(Long userId) {
        return repository.countByUser_Id(userId);
    }
}
