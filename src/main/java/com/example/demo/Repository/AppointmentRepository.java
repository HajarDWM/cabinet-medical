package com.example.demo.Repository;

import java.util.List;

import com.example.demo.DTO.AppointmentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Appointment;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	
	List<Appointment> findByPatient_IdPatient(Long idPatient);
	@Query("SELECT new com.example.demo.DTO.AppointmentDTO(a.idAppoint, a.dateTime, a.reason) FROM Appointment a")
	List<AppointmentDTO> findAllAsDTO();

}
