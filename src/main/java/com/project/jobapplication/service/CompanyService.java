package com.project.jobapplication.service;

import com.project.jobapplication.entity.Company;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CompanyService {
	List<Company> findAll();
	void createCompany(Company company);
	Company findCompanyById(Long id);
	Boolean deleteCompany(Long id);
	Boolean updateCompany(Long id, Company company);

}
