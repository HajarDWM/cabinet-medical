package com.example.demo.Controller;

import java.util.List;

import com.example.demo.DTO.MedicalRecordCreateDTO;
import com.example.demo.DTO.MedicalRecordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.MedicalRecord;
import com.example.demo.service.MedicalRecordService;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/medical-records")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping
    public List<MedicalRecordDTO> getAllDTO() {
        return medicalRecordService.getAllDTO();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecord> getById(@PathVariable Long id) {
        return medicalRecordService.getById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MedicalRecordDTO> create(
            @Valid @RequestBody MedicalRecordCreateDTO dto) {
        return ResponseEntity.ok(medicalRecordService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecordDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody MedicalRecordCreateDTO dto) {
        return ResponseEntity.ok(medicalRecordService.update(id, dto));
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        medicalRecordService.delete(id);
    }
}
