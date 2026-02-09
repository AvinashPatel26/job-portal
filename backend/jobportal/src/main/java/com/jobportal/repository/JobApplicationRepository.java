package com.jobportal.repository;

import com.jobportal.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {

    // ===== BASIC =====

    Long countByApplicant_Id(Long userId);

    List<JobApplication> findByApplicant_Id(Long userId);

    boolean existsByApplicant_IdAndJob_Id(Long userId, Long jobId);


    // ===== DASHBOARD ANALYTICS =====

    @Query("""
        SELECT 
            FUNCTION('TO_CHAR', a.id, 'MM') as month,
            COUNT(a) as count
        FROM JobApplication a
        WHERE a.applicant.id = :userId
        GROUP BY FUNCTION('TO_CHAR', a.id, 'MM')
        ORDER BY month
    """)
    List<Map<String, Object>> countApplicationsPerMonth(Long userId);


    @Query("""
        SELECT 
            a.status as name,
            COUNT(a) as value
        FROM JobApplication a
        WHERE a.applicant.id = :userId
        GROUP BY a.status
    """)
    List<Map<String, Object>> countByStatus(Long userId);
}
