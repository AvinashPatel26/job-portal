package com.jobportal.repository;

import com.jobportal.model.SavedJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedJobRepository
        extends JpaRepository<SavedJob, Long> {

    boolean existsByUser_IdAndJob_Id(Long userId, Long jobId);

    List<SavedJob> findByUser_Id(Long userId);

    void deleteByUser_IdAndJob_Id(Long userId, Long jobId);

    Long countByUser_Id(Long userId);
}
