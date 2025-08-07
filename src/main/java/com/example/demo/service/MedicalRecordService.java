package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.MedicalRecord;
import com.example.demo.Repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

    

	public List<MedicalRecord> getAll() {
        return medicalRecordRepository.findAll();
    }

    public MedicalRecord save(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

    public Optional<MedicalRecord> getById(Long id) {
        return medicalRecordRepository.findById(id);
    }

    public void delete(Long id) {
        medicalRecordRepository.deleteById(id);
    }

    public List<MedicalRecord> getByPatientId(Long idPatient) {
        return medicalRecordRepository.findByPatient_IdPatient(idPatient);
    }

}
