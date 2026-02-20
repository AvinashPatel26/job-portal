package com.jobportal.service;

import com.jobportal.model.Job;
import com.jobportal.model.JobApplication;
import com.jobportal.model.User;
import com.jobportal.repository.JobApplicationRepository;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository repository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    

    // OPTIONAL — only if you created NotificationService
    private final NotificationService notificationService;

    // OPTIONAL — WebSocket realtime updates
    private final SimpMessagingTemplate messagingTemplate;


    // =====================================================
    // APPLY JOB
    // =====================================================
    public void applyJob(Long userId, Long jobId) {

        if (repository.existsByApplicant_IdAndJob_Id(userId, jobId)) {
            throw new RuntimeException("You already applied for this job");
        }

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        JobApplication application = JobApplication.builder()
                .job(job)
                .applicant(user)
                .status("APPLIED")
                .build();

        repository.save(application);

        // notification (safe)
        if (notificationService != null) {
            notificationService.createNotification(
                    userId,
                    "You applied for " + job.getTitle()
            );
        }
    }


    // =====================================================
    // GET USER APPLICATIONS
    // =====================================================
    public List<JobApplication> getUserApplications(Long userId) {
        return repository.findByApplicant_Id(userId);
    }


    // =====================================================
    // UPDATE STATUS (EMPLOYER SIDE)
    // =====================================================
    public void updateStatus(Long id, String status) {

        JobApplication application =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Application not found"));

        application.setStatus(status);
        repository.save(application);

        // notify applicant
        if (notificationService != null) {
            notificationService.createNotification(
                    application.getApplicant().getId(),
                    "Your application status changed to " + status
            );
        }

        // realtime update (optional)
        if (messagingTemplate != null) {
            messagingTemplate.convertAndSend(
                    "/topic/applications",
                    application
            );
        }
    }


    // =====================================================
    // RESUME UPLOAD
    // =====================================================
    public void uploadResume(Long applicationId, MultipartFile file) {

        try {

            JobApplication application =
                    repository.findById(applicationId)
                            .orElseThrow(() ->
                                    new RuntimeException("Application not found"));

            String fileName =
                    System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path uploadPath =
                    Paths.get("uploads/resumes/" + fileName);

            Files.createDirectories(uploadPath.getParent());
            Files.write(uploadPath, file.getBytes());

            application.setResumePath(fileName);
            repository.save(application);

        } catch (Exception e) {
            throw new RuntimeException("Resume upload failed");
        }
    }


    // =====================================================
    // RECRUITER NOTES
    // =====================================================
    public void addRecruiterNotes(Long applicationId, String notes) {

        JobApplication application =
                repository.findById(applicationId)
                        .orElseThrow(() ->
                                new RuntimeException("Application not found"));

        application.setRecruiterNotes(notes);
        repository.save(application);
    }


    // =====================================================
    // SCHEDULE INTERVIEW
    // =====================================================
    public void scheduleInterview(Long applicationId,
                                  LocalDateTime interviewDate) {

        JobApplication application =
                repository.findById(applicationId)
                        .orElseThrow(() ->
                                new RuntimeException("Application not found"));

        application.setInterviewDate(interviewDate);
        application.setStatus("INTERVIEW");

        repository.save(application);
    }
    public List<JobApplication> getApplicationsByJob(Long jobId) {
        return repository.findByJob_Id(jobId);
    }

}
