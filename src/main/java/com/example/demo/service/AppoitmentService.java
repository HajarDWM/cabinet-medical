package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.DTO.AppointmentCreateDTO;
import com.example.demo.DTO.AppointmentDTO;
import com.example.demo.DTO.AppointmentUpdateDTO;
import com.example.demo.Entity.Patient;
import com.example.demo.Repository.AppointmentRepository;

import com.example.demo.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Appointment;

@Service

public class AppoitmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private PatientRepository patientRepository;

	private AppointmentDTO toDTO(Appointment a) {
		return new AppointmentDTO(a.getIdAppoint(), a.getDateTime(), a.getReason());
	}


	public List<AppointmentDTO> getAllDTO(){
		return appointmentRepository.findAllAsDTO();
	}
	
	public Optional<Appointment> getById(Long id){
		return appointmentRepository.findById(id);
	}

	// CREATE from DTO
	public AppointmentDTO create(AppointmentCreateDTO dto) {
		Appointment a = new Appointment();
		a.setDateTime(dto.getDateTime());
		a.setReason(dto.getReason());

		// ربط المريض (getReference كيحقن FK بلا select)
		Patient p = patientRepository.getReferenceById(dto.getPatientId());
		a.setPatient(p);

		a = appointmentRepository.save(a);
		return toDTO(a);
	}

	// UPDATE from DTO
	public AppointmentDTO update(Long id, AppointmentUpdateDTO dto) {
		Appointment a = appointmentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Appointment not found"));

		if (dto.getDateTime() != null) a.setDateTime(dto.getDateTime());
		if (dto.getReason() != null && !dto.getReason().isBlank()) a.setReason(dto.getReason());
		if (dto.getPatientId() != null) {
			Patient p = patientRepository.getReferenceById(dto.getPatientId());
			a.setPatient(p);
		}

		a = appointmentRepository.save(a);
		return toDTO(a);
	}

	
	public void delete(Long id) {
		appointmentRepository.deleteById(id);
	}
	
	public List<Appointment> getByPatientId(Long idPatient) {
        return appointmentRepository.findByPatient_IdPatient(idPatient);
    }
}
