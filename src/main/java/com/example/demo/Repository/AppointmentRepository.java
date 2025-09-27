package com.example.demo.Repository;

import com.example.demo.DTO.AppointmentDTO;
import com.example.demo.Entity.Appointment;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;




import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	// لائحة كاملة (مرتبة زمنيًا)
	@Query("""
      SELECT new com.example.demo.DTO.AppointmentDTO(
        a.idAppoint,
        p.idPatient,
        CASE
          WHEN p IS NOT NULL AND p.fullName IS NOT NULL AND p.fullName <> '' THEN p.fullName
          ELSE CONCAT('Patient #', str(p.idPatient))
        END,
        a.dateTime,
        a.reason
      )
      FROM Appointment a
      LEFT JOIN a.patient p
      ORDER BY a.dateTime DESC
    """)
	List<AppointmentDTO> findAllAsDTO();

	// واحد بالـID
	@Query("""
      SELECT new com.example.demo.DTO.AppointmentDTO(
        a.idAppoint, p.idPatient,
        CASE
          WHEN p IS NOT NULL AND p.fullName IS NOT NULL AND p.fullName <> '' THEN p.fullName
          ELSE CONCAT('Patient #', str(p.idPatient))
        END,
        a.dateTime, a.reason
      )
      FROM Appointment a
      LEFT JOIN a.patient p
      WHERE a.idAppoint = :id
    """)
	Optional<AppointmentDTO> findDTOById(Long id);

	// جميع المواعيد ديال مريض
	@Query("""
      SELECT new com.example.demo.DTO.AppointmentDTO(
        a.idAppoint, p.idPatient,
        CASE
          WHEN p IS NOT NULL AND p.fullName IS NOT NULL AND p.fullName <> '' THEN p.fullName
          ELSE CONCAT('Patient #', str(p.idPatient))
        END,
        a.dateTime, a.reason
      )
      FROM Appointment a
      LEFT JOIN a.patient p
      WHERE p.idPatient = :patientId
      ORDER BY a.dateTime DESC
    """)
	List<AppointmentDTO> findAllDTOByPatientId(Long patientId);

	// بحث (بالاسم أو السبب)
	@Query("""
      SELECT new com.example.demo.DTO.AppointmentDTO(
        a.idAppoint, p.idPatient,
        CASE
          WHEN p IS NOT NULL AND p.fullName IS NOT NULL AND p.fullName <> '' THEN p.fullName
          ELSE CONCAT('Patient #', str(p.idPatient))
        END,
        a.dateTime, a.reason
      )
      FROM Appointment a
      LEFT JOIN a.patient p
      WHERE (:q IS NULL OR :q = '' 
             OR LOWER(a.reason) LIKE LOWER(CONCAT('%', :q, '%'))
             OR (p IS NOT NULL AND LOWER(p.fullName) LIKE LOWER(CONCAT('%', :q, '%'))))
      ORDER BY a.dateTime DESC
    """)
	List<AppointmentDTO> searchDTO(String q);


	// بحث (بالاسم أو السبب) مع الترقيم
	@Query("""
      SELECT new com.example.demo.DTO.AppointmentDTO(
        a.idAppoint, p.idPatient,
        CASE
          WHEN p IS NOT NULL AND p.fullName IS NOT NULL AND p.fullName <> '' THEN p.fullName
          ELSE CONCAT('Patient #', str(p.idPatient))
        END,
        a.dateTime, a.reason
      )
      FROM Appointment a
      LEFT JOIN a.patient p
      WHERE (:q IS NULL OR :q = '' 
             OR LOWER(a.reason) LIKE LOWER(CONCAT('%', :q, '%'))
             OR (p IS NOT NULL AND LOWER(p.fullName) LIKE LOWER(CONCAT('%', :q, '%'))))
      ORDER BY a.dateTime DESC
    """)
	org.springframework.data.domain.Page<AppointmentDTO> searchDTOPaged(String q, org.springframework.data.domain.Pageable pageable);




	@Query("""
        SELECT new com.example.demo.DTO.AppointmentDTO(
            a.idAppoint,
            p.idPatient,
            CASE
                WHEN p IS NOT NULL AND p.fullName IS NOT NULL AND p.fullName <> '' THEN p.fullName
                ELSE CONCAT('Patient #', str(p.idPatient))
            END,
            a.dateTime,
            a.reason
        )
        FROM Appointment a
        LEFT JOIN a.patient p
        ORDER BY a.dateTime DESC
    """)
	Page<AppointmentDTO> findAllAsDTOPaged(Pageable pageable);

	default Page<AppointmentDTO> findAppointmentsPaged(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "idAppoint"));
		return findAllAsDTOPaged(pageable);
	}


}
