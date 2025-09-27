package com.example.demo.service;

import java.util.List;
import java.util.Optional;


import com.example.demo.DTO.PatientDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Patient;
import com.example.demo.Repository.PatientRepository;

@Service

public class PatientService {
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private PatientRepository repo;





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

	@Transactional
	public PatientDTO update(Long id, PatientDTO dto) {
		Patient existing = repo.findById(id)
				.orElseThrow(() -> new RuntimeException("Patient not found: " + id));

		// ✅ بدّل غير اللي وصل (patch style)
		if (dto.getFullName() != null)       existing.setFullName(dto.getFullName());
		if (dto.getBirthDate() != null)      existing.setBirthDate(dto.getBirthDate());
		if (dto.getPhone() != null)          existing.setPhone(dto.getPhone());
		if (dto.getAddress() != null)        existing.setAddress(dto.getAddress());
		if (dto.getMaritalStatus() != null)  existing.setMaritalStatus(dto.getMaritalStatus());
		if (dto.getBloodType() != null)      existing.setBloodType(dto.getBloodType());
		if (dto.getNotes() != null)          existing.setNotes(dto.getNotes());

		Patient saved = repo.save(existing);

		// رجّع DTO للواجهة
		PatientDTO out = new PatientDTO();
		out.setIdPatient(saved.getIdPatient());
		out.setFullName(saved.getFullName());
		out.setBirthDate(saved.getBirthDate());
		out.setPhone(saved.getPhone());
		out.setAddress(saved.getAddress());
		out.setMaritalStatus(saved.getMaritalStatus());
		out.setBloodType(saved.getBloodType());
		out.setNotes(saved.getNotes());
		return out;
	}
/// pagination
public Page<PatientDTO> getPatientsDTOPage(int page, int size) {
	Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "idPatient"));
	return patientRepository.findAllPatientsDTO(pageable);
}

}

