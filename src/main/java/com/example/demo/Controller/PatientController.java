package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.DTO.PatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.Entity.Patient;
import com.example.demo.service.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/patients")

public class PatientController {
	
	@Autowired
	private PatientService patientService;



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
	 
	 @GetMapping("/search")
	    public List<Patient> search(@RequestParam String name) {
	        return patientService.searchByName(name);
	    }
}
