package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.DTO.PatientDTO;
import com.example.demo.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.DTO.PatientLiteDTO;
import com.example.demo.Entity.Patient;
import com.example.demo.service.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/patients")

public class PatientController {
	
	@Autowired
	private PatientService patientService;
	@Autowired
	private PatientRepository patientRepository;



	//// hadi end point li katjib jami3 les patients b dto li 3ad sawebtiha Get /api/patients

	@GetMapping()
	public List<PatientDTO> getPatientsDTO() {
		return patientService.getAllPatientsDTO();
	}
	
	 @GetMapping("/{id}")
	    public ResponseEntity<Patient> getById(@PathVariable Long id) {
	        return patientService.getById(id)
	            .map(ResponseEntity::ok)
	            .orElse(ResponseEntity.notFound().build());
	    }

		/// / hadi end point li katjib les patients b pagination li 3ad sawebtiha  Get /api/patients/paged
		/// fiha parametre dyal page w size li homa optional ila ma 3titiwhomch kayakhod default values
		/// page default 0 w size default 10
		/// exemple: /api/patients/paged?page=1&size=5
		/// hadi katjib page 1 w size 5 yani katjib 5 patients
	@GetMapping("/paged")
	public Page<PatientDTO> getPatientsPaged(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size
	) {
		Pageable pageable = PageRequest.of(page, size);
		return patientService.getPatientsDTOPage(page, size);
	}
	 
	 @PostMapping
	 public Patient create(@Valid @RequestBody Patient patient) {
		 return patientService.save(patient);
	 }

	@PutMapping("/{id}")
	public ResponseEntity<Patient> update(@PathVariable("id") Long id,@Valid @RequestBody Patient updated) {
		if (updated == null) {
			return ResponseEntity.badRequest().build(); // ✅ حماية من null
		}

		return patientService.getById(id).map(existing -> {
			updated.setIdPatient(id);
			return ResponseEntity.ok(patientService.save(updated));
		}).orElse(ResponseEntity.notFound().build());
	}





	@DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	        patientService.delete(id);
	    }




	@GetMapping("/search") // hadi end point li kat9lb 3la patient b smiytou w katrja3 list dyal PatientLiteDTO
	public List<PatientLiteDTO> searchPatients(
			@RequestParam("q") String q, ///  hada query li ghadi ykoun smiya dyal patient
			@RequestParam(value = "limit", defaultValue = "10") int limit // had limit li ghadi ykoun 3adad dyal les results li bghiti tjiini
	) {
		if (q == null || q.trim().length() < 2) {
			throw new IllegalArgumentException("Query must be at least 2 characters");
		}
		int maxLimit = Math.min(limit, 10);
		return patientRepository.searchLiteByFullName(q.trim(), PageRequest.of(0, maxLimit));
	}




}
