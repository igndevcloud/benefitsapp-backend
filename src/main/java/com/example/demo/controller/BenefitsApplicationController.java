package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.BenefitsApplication;
import com.example.demo.repository.BenefitsApplicationRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class BenefitsApplicationController {

	@Autowired
	private BenefitsApplicationRepository benefitsApplicationRepository;
	
	//get all data
	@CrossOrigin
	@GetMapping("/benefitsApplications")
	public List <BenefitsApplication> getAllBenefitsApplications(){
		return benefitsApplicationRepository.findAll();
	}  
		
	//create 
	@CrossOrigin
	@PostMapping("/benefitsApplications")
	public BenefitsApplication createBenefitsApplication(@RequestBody BenefitsApplication benefitsApplication)
	{
		return benefitsApplicationRepository.save(benefitsApplication);
	}
	
	
	// get data by id 
	@CrossOrigin
	@GetMapping("/benefitsApplications/{id}")
	public ResponseEntity<BenefitsApplication> getByID(@PathVariable Long id) {
		BenefitsApplication benefitsApplication = benefitsApplicationRepository.findById(id).
				orElseThrow(()-> new ResourceNotFoundException("benefitsApplication with id "+id+"does not exists"));
		return ResponseEntity.ok(benefitsApplication);
	}
	
	@CrossOrigin
	@GetMapping("/benefitsApplications/getbyUser/{email}")
	public List <BenefitsApplication> getByID(@PathVariable String email) {
		List<BenefitsApplication> benefitsApplications = benefitsApplicationRepository.findByEmail(email);
		return benefitsApplications;
	}	
	
	//update data 
	@CrossOrigin
	@PutMapping ("/benefitsApplications/{id}")
	public ResponseEntity<BenefitsApplication> updateBenefitsApplicationByID(@PathVariable Long id, @RequestBody BenefitsApplication benefitsApplicationDetails){
		BenefitsApplication benefitsApplication = benefitsApplicationRepository.findById(id).
				orElseThrow(()-> new ResourceNotFoundException("benefitsApplication with id "+id+"does not exists"));
		
		
		benefitsApplication.setFname(benefitsApplicationDetails.getFname());
		benefitsApplication.setLname(benefitsApplicationDetails.getLname());
		benefitsApplication.setEmail(benefitsApplicationDetails.getEmail());
		benefitsApplication.setAddress(benefitsApplicationDetails.getAddress());
		benefitsApplication.setCity(benefitsApplicationDetails.getCity());
		benefitsApplication.setStartDate(benefitsApplicationDetails.getStartDate());
		
		BenefitsApplication updatedbenefitsApplication=benefitsApplicationRepository.save(benefitsApplication);
		
		return ResponseEntity.ok(updatedbenefitsApplication);
}
	
		//update data 
		@CrossOrigin
		@PutMapping ("/benefitsApplications/approve/{id}")
		public ResponseEntity<BenefitsApplication> approveBenefitsApplicationByID(@PathVariable Long id, @RequestBody BenefitsApplication benefitsApplicationDetails){
			BenefitsApplication benefitsApplication = benefitsApplicationRepository.findById(id).
					orElseThrow(()-> new ResourceNotFoundException("benefitsApplication with id "+id+"does not exists"));
			
			benefitsApplication.setApproval("Approved");
			
			BenefitsApplication updatedbenefitsApplication=benefitsApplicationRepository.save(benefitsApplication);
			
			return ResponseEntity.ok(updatedbenefitsApplication);
	}



		//update data 
		@CrossOrigin
		@PutMapping ("/benefitsApplications/decline/{id}")
		public ResponseEntity<BenefitsApplication> declineBenefitsApplicationByID(@PathVariable Long id, @RequestBody BenefitsApplication benefitsApplicationDetails){
			BenefitsApplication benefitsApplication = benefitsApplicationRepository.findById(id).
					orElseThrow(()-> new ResourceNotFoundException("benefitsApplication with id "+id+"does not exists"));
			
			benefitsApplication.setApproval("Declined");
			
			BenefitsApplication updatedbenefitsApplication=benefitsApplicationRepository.save(benefitsApplication);
			
			return ResponseEntity.ok(updatedbenefitsApplication);
	}	
	
	@CrossOrigin
	@DeleteMapping("/benefitsApplications/{id}")
	public ResponseEntity <Map<String, Boolean> >deletebenefitsApplication(@PathVariable Long id){
		
		
		BenefitsApplication benefitsApplication = benefitsApplicationRepository.findById(id).
				orElseThrow(()-> new ResourceNotFoundException("benefitsApplication with id "+id+"does not exists"));
		
		benefitsApplicationRepository.delete(benefitsApplication);
		
		Map<String, Boolean>  response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	
}
	
	
	
	
	
	
	
	
	
	
}