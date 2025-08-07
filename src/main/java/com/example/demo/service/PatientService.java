package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.DTO.PatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Patient;
import com.example.demo.Repository.PatientRepository;

@Service

public class PatientService {
	@Autowired
	private PatientRepository patientRepository;
	
	
	
// jalb jami3 patient 
    public List<PatientDTO> getAllPatientsDTO() {
	     return patientRepository.findAllPatientsDTO();
    }
	
// jalb patient 7asaba Id 
	public Optional<Patient> getById(Long id){
		return patientRepository.findById(id);
	}
	
// idafat aw ta3dil patient 
	public Patient save(Patient patient) {
		return patientRepository.save(patient);
	}
	
// mash patient 
	public void delete(Long id) {
		patientRepository.deleteById(id);
	}

	public List<Patient> searchByName(String name) {
		
		return patientRepository.findByFullNameContainingIgnoreCase(name);
	}
}
