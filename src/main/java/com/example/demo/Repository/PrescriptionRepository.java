package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.DTO.PrescriptionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Prescription;
@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

	Optional<Prescription> findByConsultation_IdConsul(Long idConsul);

	@Query("SELECT new com.example.demo.DTO.PrescriptionDTO(p.idPerscription, p.medication, p.dosage, p.duration, p.notes, p.consultation.idConsul) " +
			"FROM Prescription p")
	List<PrescriptionDTO> findAllAsDTO();
}
