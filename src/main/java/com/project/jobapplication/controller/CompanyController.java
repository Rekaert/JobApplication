package com.project.jobapplication.controller;

import com.project.jobapplication.entity.Company;
import com.project.jobapplication.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

	CompanyService companyService;
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@GetMapping("/companies")
	private ResponseEntity<List<Company>> finfAll() {
		return ResponseEntity.ok(companyService.findAll());
	}

	@PostMapping("/companies")
	private ResponseEntity<String> createCompany(@RequestBody Company company) {
		companyService.createCompany(company);
		return new ResponseEntity<>("New company created", HttpStatus.CREATED);
	}

	@GetMapping("/companies/{id}")
	private ResponseEntity<Company> findById(@PathVariable Long id) {
		Company company = companyService.findCompanyById(id);
		return company != null ? new ResponseEntity<Company>(company, HttpStatus.OK) : new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/companies/{id}")
	private ResponseEntity<String> deleteById(@PathVariable Long id) {
		Boolean isDeleted = companyService.deleteCompany(id);
		return isDeleted ? new ResponseEntity<>("Company deleted successfully", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/companies/{id}")
	private ResponseEntity<String> updateById(@PathVariable Long id, @RequestBody Company company) {
		Boolean isUpdated = companyService.updateCompany(id, company);
		return isUpdated ? new ResponseEntity<>("Company updated successfully", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
