package com.example.demo.Repository;

import com.example.demo.DTO.PatientLiteDTO;
import org.springframework.data.domain.Pageable; // <-- correct import
import java.util.List;

import com.example.demo.DTO.PatientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.Entity.Patient;
import com.example.demo.DTO.PatientLiteDTO;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	List<Patient> findByFullNameContainingIgnoreCase(String FullName);

	@Query("SELECT new com.example.demo.DTO.PatientDTO(p.notes, p.bloodType, p.maritalStatus, p.address, p.phone, p.birthDate, p.fullName, p.idPatient) FROM Patient p")
	List<PatientDTO> findAllPatientsDTO();

	@Query("SELECT new com.example.demo.DTO.PatientLiteDTO(p.idPatient, p.fullName) FROM Patient p")
	List<PatientLiteDTO> findAllLite();


	@Query("""
      select p from Patient p
      where lower(p.fullName) like lower(concat('%', :name, '%'))
      order by p.fullName asc
    """)
	List<Patient> searchByName(@Param("name") String name, Pageable pageable);

	@Query("SELECT new com.example.demo.DTO.PatientDTO(p.notes, p.bloodType, p.maritalStatus, p.address, p.phone, p.birthDate, p.fullName, p.idPatient) FROM Patient p")
	Page<PatientDTO> findAllPatientsDTO(org.springframework.data.domain.Pageable pageable);

	@Query("SELECT p FROM Patient p WHERE LOWER(p.fullName) LIKE LOWER(CONCAT('%', :q, '%')) ORDER BY p.fullName ASC")
	List<Patient> searchByFullName(@Param("q") String q, Pageable pageable);

	List<Patient> findTop10ByFullNameContainingIgnoreCaseOrderByFullNameAsc(String q);

	@Query("SELECT new com.example.demo.DTO.PatientLiteDTO(p.idPatient, p.fullName) " +
			"FROM Patient p WHERE LOWER(p.fullName) LIKE LOWER(CONCAT('%', :q, '%')) ORDER BY p.fullName ASC")
	List<PatientLiteDTO> searchLiteByFullName(@Param("q") String q, Pageable pageable);

}
