package com.example.demo.Controller;

import java.util.List;

import com.example.demo.DTO.PrescriptionDTO;
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
    public List<PrescriptionDTO> getAllDTO() {
        return prescriptionService.getAllDTO();

    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionDTO> getById(@PathVariable Long id) {
        return prescriptionService.getById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PrescriptionDTO> create(@Valid @RequestBody PrescriptionDTO dto) {

        return ResponseEntity.ok(prescriptionService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrescriptionDTO> update(@PathVariable Long id,
                                                     @Valid @RequestBody PrescriptionDTO dto) {
        return ResponseEntity.ok(prescriptionService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        prescriptionService.delete(id);
    }

}
