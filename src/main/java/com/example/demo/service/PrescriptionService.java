package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Prescription;
import com.example.demo.Repository.PrescriptionRepository;

@Service
public class PrescriptionService {
	@Autowired
	 private PrescriptionRepository prescriptionRepository;


		public List<Prescription> getAll() {
	        return prescriptionRepository.findAll();
	    }

	    public Prescription save(Prescription prescription) {
	        return prescriptionRepository.save(prescription);
	    }

	    public Optional<Prescription> getById(Long id) {
	        return prescriptionRepository.findById(id);
	    }

	    public void delete(Long id) {
	        prescriptionRepository.deleteById(id);
	    }

	    public Optional<Prescription> getByIdConsultation(Long idConsul) {
	        return prescriptionRepository.findByConsultation_IdConsul(idConsul);
	    }
}
