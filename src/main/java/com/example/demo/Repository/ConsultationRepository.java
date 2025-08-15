package com.example.demo.Repository;

import java.util.List;

import com.example.demo.DTO.ConsultationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Consultation;
@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long>{
	
	 List<Consultation> findByPatient_IdPatient(Long idPatient);
	@Query("select new com.example.demo.DTO.ConsultationDTO(c.idConsul, c.date,c.symptoms,c.diagnosis,c.notes) from Consultation c")
	 List<ConsultationDTO> findAllAsDTO();




}
