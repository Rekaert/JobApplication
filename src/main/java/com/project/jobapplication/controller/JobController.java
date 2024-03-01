package com.project.jobapplication.controller;

import com.project.jobapplication.entity.Job;
import com.project.jobapplication.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {

	JobService jobService;

	public JobController(JobService jobService) {
		this.jobService = jobService;
	}

	@GetMapping("/jobs")
	private ResponseEntity<List<Job>> findAll() {
		return ResponseEntity.ok(jobService.findAll());
	}

	@PostMapping("/jobs")
	private ResponseEntity<String> createJob(@RequestBody Job job) {
		jobService.createJob(job);
		return new ResponseEntity<>("New job processed successfully", HttpStatus.CREATED);
	}

	@GetMapping("/jobs/{id}")
	private ResponseEntity<Job> findJobById(@PathVariable Long id) {
		Job job = jobService.findJobById(id);
		return job != null ? new ResponseEntity<>(job, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/jobs/{id}")
	private ResponseEntity<String> deleteJobById (@PathVariable Long id) {
		Boolean isDeleted = jobService.deleteJob(id);
		return isDeleted ? new ResponseEntity<>("Job deleted successfully", HttpStatus.OK)
							: new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
	}

	@PutMapping("/jobs/{id}")
	private ResponseEntity<String> updateJobById (@PathVariable Long id, @RequestBody Job updatedJob) {
		Boolean isUpdated = jobService.updateJob(id, updatedJob);
		return isUpdated ? new ResponseEntity<>("Job updated successfully", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
