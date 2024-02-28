package com.project.jobapplication.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {

	private Long id;
	private String title;
	private String description;
	private String minSalary;
	private String maxSalary;
	private String location;
}
