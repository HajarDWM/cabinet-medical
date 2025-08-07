package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Prescription;
@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
	
	Optional<Prescription> findByConsultation_IdConsul(Long id);

}
