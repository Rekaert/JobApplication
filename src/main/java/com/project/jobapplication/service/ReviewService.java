package com.project.jobapplication.service;

import com.project.jobapplication.entity.Review;

import java.util.List;

public interface ReviewService {
	List<Review> findAll(Long companyId);
	boolean createReview(Long companyId, Review review);
	Review findReview(Long companyId, Long id);
	boolean deleteReview(Long companyId, Long id);
	boolean updateReview(Long companyId, Long id, Review review);
}
