package com.jobportal.repository;

import com.jobportal.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    // Dashboard → Recent jobs
    List<Job> findTop5ByOrderByIdDesc();

    // Employer → Jobs posted by employer
    List<Job> findByPostedBy_Id(Long employerId);

    // Job search → location filter
    List<Job> findByLocationContainingIgnoreCase(String location);

    // ✅ Enterprise-ready additions (safe)
    
    // Search by title
    List<Job> findByTitleContainingIgnoreCase(String keyword);

    // Search by company
    List<Job> findByCompanyContainingIgnoreCase(String company);

    // Combined search (title OR location)
    List<Job> findByTitleContainingIgnoreCaseOrLocationContainingIgnoreCase(
            String title,
            String location
    );
    Long countByPostedBy_Id(Long employerId);

    long count();

}
