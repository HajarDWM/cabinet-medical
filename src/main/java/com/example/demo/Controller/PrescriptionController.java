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

import com.example.demo.Entity.Prescription;
import com.example.demo.service.PrescriptionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/prescriptions")

public class PrescriptionController {
	@Autowired
	private PrescriptionService prescriptionService;


	@GetMapping
    public List<Prescription> getAll() {
        return prescriptionService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prescription> getById(@PathVariable Long id) {
        return prescriptionService.getById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Prescription create(@Valid @RequestBody Prescription prescription) {
        return prescriptionService.save(prescription);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prescription> update(@PathVariable Long id,@Valid @RequestBody Prescription updated) {
        updated.setIdPerscription(id);
        return ResponseEntity.ok(prescriptionService.save(updated));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        prescriptionService.delete(id);
    }

}
