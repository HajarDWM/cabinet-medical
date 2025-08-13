package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.DTO.Consultation.ConsultationCreateDTO;
import com.example.demo.DTO.Consultation.ConsultationDTO;
import com.example.demo.DTO.Consultation.ConsultationUpdateDTO;

import com.example.demo.Entity.Patient;

import com.example.demo.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Consultation;
import com.example.demo.Repository.ConsultationRepository;

@Service
public class ConsultationService {
	@Autowired private ConsultationRepository consultationRepository;
	@Autowired private PatientRepository patientRepository;





	public List<ConsultationDTO> getAllDTO(){ return consultationRepository.findAllAsDTO(); }
	
	 public Optional<Consultation> getById(Long id) {
	        return consultationRepository.findById(id);
    }

	public ConsultationDTO create(ConsultationCreateDTO dto) {
		Consultation c = new Consultation();
		c.setDate(dto.getDate());
		c.setSymptoms(dto.getSymptoms());
		c.setDiagnosis(dto.getDiagnosis());
		c.setNotes(dto.getNotes());

		Patient p = patientRepository.getReferenceById(dto.getPatientId());
		c.setPatient(p);

		c = consultationRepository.save(c);
		return toDTO(c);

	}

	public ConsultationDTO update(Long id, ConsultationUpdateDTO dto) {
		Consultation c = consultationRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Consultation not found"));

		if (dto.getDate() != null) c.setDate(dto.getDate());
		if (dto.getSymptoms() != null) c.setSymptoms(dto.getSymptoms());
		if (dto.getDiagnosis() != null) c.setDiagnosis(dto.getDiagnosis());
		if (dto.getNotes() != null) c.setNotes(dto.getNotes());
		if (dto.getPatientId() != null) {
			Patient p = patientRepository.getReferenceById(dto.getPatientId());
			c.setPatient(p);
		}

		c = consultationRepository.save(c);
		return toDTO(c);

		}

	 
	 public void delete(Long id) {
		 consultationRepository.deleteById(id);
	 }
	// ✅ ميثود التحويل إلى DTO
	private ConsultationDTO toDTO(Consultation c) {
		return new ConsultationDTO(
				c.getIdConsul(),
				c.getDate(),
				c.getSymptoms(),
				c.getDiagnosis(),
				c.getNotes()
		);
	}
	 public List<Consultation> getByIdPatient(Long idPatient){
		 return consultationRepository.findByPatient_IdPatient(idPatient);
	 }

}
