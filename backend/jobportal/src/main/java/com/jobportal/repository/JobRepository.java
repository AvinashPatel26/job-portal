package com.jobportal.repository;

import com.jobportal.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByLocationContainingIgnoreCase(String location);

	List<Job> findTop5ByOrderByIdDesc();

}
