package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Condition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Consultation;
import com.example.demo.Repository.ConsultationRepository;

@Service
public class ConsultationService {
	@Autowired
	private ConsultationRepository consultationRepository;
	
	
	

	public List<Consultation> getAll(){
		return consultationRepository.findAll();
	}
	
	 public Optional<Consultation> getById(Long id) {
	        return consultationRepository.findById(id);
    }
	 
	 public Consultation save(Consultation consultation) {
		 return consultationRepository.save(consultation);
	 }
	 
	 public void delete(Long id) {
		 consultationRepository.deleteById(id);
	 }
	 
	 public List<Consultation> getByIdPatient(Long idPatient){
		 return consultationRepository.findByPatient_IdPatient(idPatient);
	 }

}
