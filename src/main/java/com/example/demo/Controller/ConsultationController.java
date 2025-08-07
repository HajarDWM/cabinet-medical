package com.example.demo.Controller;

import java.util.List;

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
	    public List<Consultation> getAll() {
	        return consultationService.getAll();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Consultation> getById(@PathVariable Long id) {
	        return consultationService.getById(id)
	            .map(ResponseEntity::ok)
	            .orElse(ResponseEntity.notFound().build());
	    }

	    @PostMapping
	    public Consultation create(@Valid @RequestBody Consultation consultation) {
	        return consultationService.save(consultation);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Consultation> update(@PathVariable Long id,@Valid @RequestBody Consultation updated) {
	        updated.setIdConsul(id);
	        return ResponseEntity.ok(consultationService.save(updated));
	    }

	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	        consultationService.delete(id);
	    }
}
