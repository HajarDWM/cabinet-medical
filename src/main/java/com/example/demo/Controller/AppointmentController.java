package com.example.demo.Controller;

import java.util.List;

import com.example.demo.DTO.Appointment.AppointmentCreateDTO;
import com.example.demo.DTO.Appointment.AppointmentDTO;
import com.example.demo.DTO.Appointment.AppointmentUpdateDTO;
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

import com.example.demo.Entity.Appointment;
import com.example.demo.service.AppoitmentService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
	@Autowired
	private AppoitmentService appoitmentService;
	
    

	@GetMapping
    public List<AppointmentDTO> getAllDTO() {
        return appoitmentService.getAllDTO();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getById(@PathVariable Long id) {
        return appoitmentService.getById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/patient/{patientId}")
    public List<Appointment> getByPatient(@PathVariable Long patientId) {
        return appoitmentService.getByPatientId(patientId);
    }

    // ⬇️ create بـ DTO
    @PostMapping
    public ResponseEntity<AppointmentDTO> create(@Valid @RequestBody AppointmentCreateDTO dto) {
        return ResponseEntity.ok(appoitmentService.create(dto));
    }

    // ⬇️ update بـ DTO
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDTO> update(@PathVariable Long id,
                                                 @Valid @RequestBody AppointmentUpdateDTO dto) {
        return ResponseEntity.ok(appoitmentService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    	appoitmentService.delete(id);
    }
	

}
