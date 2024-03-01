package com.project.jobapplication.repository;

import com.project.jobapplication.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
