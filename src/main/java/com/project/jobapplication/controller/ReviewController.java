package com.project.jobapplication.controller;

import com.project.jobapplication.entity.Review;
import com.project.jobapplication.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

	public ReviewService reviewService;
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@GetMapping("/reviews")
	public ResponseEntity<List<Review>> findAllReviews(@PathVariable Long companyId) {
		return ResponseEntity.ok(reviewService.findAll(companyId));
	}

	@PostMapping("/reviews")
	public ResponseEntity<String> createReview(@PathVariable Long companyId, @RequestBody Review review) {
		boolean isCreated = reviewService.createReview(companyId, review);
		return isCreated ? new ResponseEntity("Review created successfully", HttpStatus.CREATED) : new ResponseEntity<>("Review is not created", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/reviews/{id}")
	public ResponseEntity<Review> findReview(@PathVariable Long companyId, @PathVariable Long id) {
		Review review = reviewService.findReview(companyId, id);
		return review != null ? new ResponseEntity<>(review, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("reviews/{id}")
	public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long id) {
		boolean isDeleted = reviewService.deleteReview(companyId, id);
		return isDeleted ? new ResponseEntity<>("Review deleted successfully", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("reviews/{id}")
	public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long id, @RequestBody Review review) {
		boolean isUpdated = reviewService.updateReview(companyId, id, review);
		return isUpdated ? new ResponseEntity<>("Review updated successfully", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
