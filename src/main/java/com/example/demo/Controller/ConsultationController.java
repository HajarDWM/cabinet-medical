package com.example.demo.Controller;

import java.util.List;

import com.example.demo.DTO.ConsultationCreateDTO;
import com.example.demo.DTO.ConsultationDTO;
import com.example.demo.DTO.ConsultationUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Consultation;
import com.example.demo.service.ConsultationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/consultations")
public class ConsultationController {
	@Autowired
	 private ConsultationService consultationService;

	 @GetMapping
	 public List<ConsultationDTO> getAllDTO() { return consultationService.getAllDTO(); }

	    @GetMapping("/{id}")
	    public ResponseEntity<Consultation> getById(@PathVariable Long id) {
	        return consultationService.getById(id)
	            .map(ResponseEntity::ok)
	            .orElse(ResponseEntity.notFound().build());
	    }

	@PostMapping
	public ResponseEntity<ConsultationDTO> create(@Valid @RequestBody ConsultationCreateDTO dto) {
		return ResponseEntity.ok(consultationService.create(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ConsultationDTO> update(@PathVariable Long id,
												  @Valid @RequestBody ConsultationUpdateDTO dto) {
		return ResponseEntity.ok(consultationService.update(id, dto));
	}

	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	        consultationService.delete(id);
	    }
}
