package com.example.demo.Controller;

import java.util.List;

import com.example.demo.DTO.AppointmentCreateDTO;
import com.example.demo.DTO.AppointmentDTO;
import com.example.demo.DTO.AppointmentUpdateDTO;
import com.example.demo.service.AppointmentService;
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



import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
	@Autowired
	private AppointmentService appointmentService;

    // ⬇️ لائحة كاملة بـ DTO
    
	@GetMapping
    public List<AppointmentDTO> getAllDTO() {
        return appointmentService.getAllDTO();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getById(@PathVariable Long id) {
        return appointmentService.getById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/patient/{patientId}")
    public List<AppointmentDTO> getByPatient(@PathVariable Long patientId) {
        return appointmentService.getByPatientId(patientId);
    }

    // ⬇️ create بـ DTO
    @PostMapping
    public ResponseEntity<AppointmentDTO> create(@Valid @RequestBody AppointmentCreateDTO dto) {
        return ResponseEntity.ok(appointmentService.create(dto));
    }

    // ⬇️ update بـ DTO
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDTO> update(@PathVariable Long id,
                                                 @Valid @RequestBody AppointmentUpdateDTO dto) {
        return ResponseEntity.ok(appointmentService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        appointmentService.delete(id);
    }


}
