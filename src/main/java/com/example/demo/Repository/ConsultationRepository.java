package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Consultation;
@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long>{
	
	 List<Consultation> findByPatient_IdPatient(Long idPatient);
	  

}
