package com.project.jobapplication.service;

import com.project.jobapplication.entity.Company;
import com.project.jobapplication.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

	public CompanyServiceImpl(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	public CompanyRepository companyRepository;

	@Override
	public List<Company> findAll() {
		return companyRepository.findAll();
	}
	@Override
	public void createCompany(Company company){
		companyRepository.save(company);
	}
	@Override
	public Company findCompanyById(Long id) {
		return companyRepository.findById(id).orElse(null);
	}
	@Override
	public Boolean deleteCompany(Long id){
		try {
			companyRepository.deleteById(id);
			return true;
		} catch (Exception e){
			return false;
		}
	}
	@Override
	public Boolean updateCompany(Long id, Company updatedCompany) {
		Optional<Company> companyOptional = companyRepository.findById(id);
		if(companyOptional.isPresent()) {
			Company company = companyOptional.get();
			company.setName(updatedCompany.getName());
			company.setDescription(updatedCompany.getDescription());
			company.setJobs(updatedCompany.getJobs());
			company.setReviews(updatedCompany.getReviews());
			companyRepository.save(company);
			return true;
		}
		return false;
	}
}
