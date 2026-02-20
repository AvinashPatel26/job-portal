package com.jobportal.repository;

import com.jobportal.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {

    // User applications
    List<JobApplication> findByApplicant_Id(Long applicantId);

    // Dashboard stats
    Long countByApplicant_Id(Long applicantId);

    // Prevent duplicate apply
    boolean existsByApplicant_IdAndJob_Id(Long applicantId, Long jobId);

    // Monthly analytics
    @Query(value = """
        SELECT TO_CHAR(created_at, 'Mon') as month,
               COUNT(*) as count
        FROM job_application
        WHERE applicant_id = :userId
        GROUP BY month
        ORDER BY month
        """, nativeQuery = true)
    List<Map<String, Object>> countApplicationsPerMonth(Long userId);

    // Status analytics
    @Query(value = """
        SELECT status as name,
               COUNT(*) as value
        FROM job_application
        WHERE applicant_id = :userId
        GROUP BY status
        """, nativeQuery = true)
    List<Map<String, Object>> countByStatus(Long userId);
    
    @Query(value = """
    	    SELECT status as name, COUNT(*) as value
    	    FROM job_application ja
    	    JOIN job j ON ja.job_id = j.id
    	    WHERE j.posted_by_id = :employerId
    	    GROUP BY status
    	""", nativeQuery = true)
    	List<Map<String, Object>> countEmployerApplicationsByStatus(Long employerId);

    @Query(value = """
    	    SELECT COUNT(*)
    	    FROM job_application ja
    	    JOIN job j ON ja.job_id = j.id
    	    WHERE j.posted_by_id = :employerId
    	    """, nativeQuery = true)
    	Long countByEmployerJobs(Long employerId);


    	@Query(value = """
    	    SELECT COUNT(*)
    	    FROM job_application ja
    	    JOIN job j ON ja.job_id = j.id
    	    WHERE j.posted_by_id = :employerId
    	    AND ja.status = :status
    	    """, nativeQuery = true)
    	Long countByEmployerAndStatus(Long employerId, String status);
    	List<JobApplication> findByJob_Id(Long jobId);

    	long count();

    	
}
