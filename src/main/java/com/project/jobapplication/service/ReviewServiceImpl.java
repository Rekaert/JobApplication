package com.project.jobapplication.service;

import com.project.jobapplication.entity.Review;
import com.project.jobapplication.entity.Company;
import com.project.jobapplication.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

	public ReviewRepository reviewRepository;
	public CompanyService companyService;
	public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
		this.reviewRepository = reviewRepository;
		this.companyService = companyService;

	}

	@Override
	public List<Review> findAll(Long companyId) {
		return reviewRepository.findByCompanyId(companyId);
	}

	@Override
	public boolean createReview(Long companyId, Review review) {
		Company company = companyService.findCompanyById(companyId);
		if(company != null) {
			review.setCompany(company);
			reviewRepository.save(review);
			return true;
		}
		return false;
	}

	@Override
	public Review findReview(Long companyId, Long id) {
		List<Review> reviews = reviewRepository.findByCompanyId(companyId);
		return reviews.stream().filter(review -> review.getId().equals(id)).findFirst().orElse(null);
	}

	@Override
	public boolean deleteReview(Long companyId, Long id) {
		if(companyService.findCompanyById(companyId) != null && companyService.findCompanyById(companyId).getReviews().contains(reviewRepository.findById(id))) {
			Review review = reviewRepository.findById(id).orElse(null);
			Company company = review.getCompany();
			company.getReviews().remove(review);
			review.setCompany(null);
			companyService.updateCompany(companyId, company);
			reviewRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateReview(Long companyId, Long id, Review updatedReview) {
		if(companyService.findCompanyById(companyId) != null){
			updatedReview.setCompany(companyService.findCompanyById(companyId));
			updatedReview.setId(id);
			reviewRepository.save(updatedReview);
			return true;
		}
		return false;
	}
}
