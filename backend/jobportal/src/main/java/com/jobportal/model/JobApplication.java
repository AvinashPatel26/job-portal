package com.jobportal.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Job job;

    @ManyToOne
    private User applicant;

    private String status;

    // NEW FIELDS
    private String resumePath;

    @Column(length = 2000)
    private String recruiterNotes;

    private LocalDateTime interviewDate;
}
