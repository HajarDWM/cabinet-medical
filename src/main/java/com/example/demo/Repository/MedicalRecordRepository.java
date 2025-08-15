package com.example.demo.Repository;

import java.util.List;

import com.example.demo.DTO.MedicalRecordDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.MedicalRecord;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
	
	List<MedicalRecord> findByPatient_IdPatient(Long idPatient);
	@Query("""
    SELECT new com.example.demo.DTO.MedicalRecordDTO( m.idMedical, m.allergies, m.creationDate, m.chronicDiseases, m.surgeries, m.notes  ) FROM MedicalRecord m
""")
	List<MedicalRecordDTO> findAllAsDTO();
	boolean existsByPatient_IdPatient(Long idPatient);



}
