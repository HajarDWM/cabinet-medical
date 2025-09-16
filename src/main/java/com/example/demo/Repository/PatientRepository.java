package com.example.demo.Repository;


import java.awt.print.Pageable;
import java.util.List;


import com.example.demo.DTO.PatientDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.example.demo.Entity.Patient;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
	
	List<Patient> findByFullNameContainingIgnoreCase(String FullName);

	///  HADI KHESSHA QUERY khali hadi hta necheha lik hitach rak fah
	 @Query("SELECT new com.example.demo.DTO.PatientDTO(p.notes, p.bloodType, p.maritalStatus, p.address, p.phone, p.birthDate, p.fullName, p.idPatient) FROM Patient p")
	 List<PatientDTO> findAllPatientsDTO();

	@Query("""
      select p from Patient p
      where lower(p.fullName) like lower(concat('%', :name, '%'))
      order by p.fullName asc
    """)
	List<Patient> searchByName(@Param("name") String name, Pageable pageable);

	//pagination
	@Query("SELECT new com.example.demo.DTO.PatientDTO(p.notes, p.bloodType, p.maritalStatus, p.address, p.phone, p.birthDate, p.fullName, p.idPatient) FROM Patient p")
	Page<PatientDTO> findAllPatientsDTO(org.springframework.data.domain.Pageable pageable);

}
