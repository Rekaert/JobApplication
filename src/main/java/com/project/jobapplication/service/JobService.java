package com.project.jobapplication.service;

import com.project.jobapplication.entity.Job;

import java.util.List;

public interface JobService {
	List<Job> findAll();
	void createJob(Job job);

	Job findJobById(Long id);

	Boolean deleteJob(Long id);

	Boolean updateJob(Long id, Job job);
}
