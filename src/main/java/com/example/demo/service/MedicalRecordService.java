package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.DTO.MedicalRecordCreateDTO;
import com.example.demo.DTO.MedicalRecordDTO;
import com.example.demo.Entity.Patient;
import com.example.demo.Repository.PatientRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.MedicalRecord;
import com.example.demo.Repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
    @Autowired
    private PatientRepository patientRepository;


public List<MedicalRecordDTO> getAllDTO() {
        return medicalRecordRepository.findAllAsDTO();
    }

    public Optional<MedicalRecord> getById(Long id) {
        return medicalRecordRepository.findById(id);
    }

    // CREATE from DTO
    public MedicalRecordDTO create(MedicalRecordCreateDTO dto) {
// تحقّق أن المريض موجود
        patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found: " + dto.getPatientId()));

        MedicalRecord m = new MedicalRecord();
        m.setCreationDate(dto.getCreationDate());
        m.setAllergies(dto.getAllergies());
        m.setChronicDiseases(dto.getChronicDiseases());
        m.setSurgeries(dto.getSurgeries());
        m.setNotes(dto.getNotes());

        // أهم سطرين: ربط الـ FK
        Patient p = patientRepository.getReferenceById(dto.getPatientId()); // proxy مُدار
        m.setPatient(p);

        m = medicalRecordRepository.saveAndFlush(m); // flush باش يبان أي خطأ FK فوراً
        return toDTO(m);
    }

    // UPDATE from DTO
    @Transactional
    public MedicalRecordDTO update(Long id, @Valid MedicalRecordCreateDTO dto) {
        MedicalRecord m = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MedicalRecord not found"));

        // لو بغيت تبدّل المريض
        if (dto.getPatientId() != null &&
                (m.getPatient() == null || !dto.getPatientId().equals(m.getPatient().getIdPatient()))) {

            // ممنوع نفس المريض يكون عندو سجل آخر
            if (medicalRecordRepository.existsByPatient_IdPatient(dto.getPatientId())) {
                throw new IllegalStateException("This patient already has a medical record");
            }
            // تأكد كاين
            patientRepository.findById(dto.getPatientId())
                    .orElseThrow(() -> new IllegalArgumentException("Patient not found: " + dto.getPatientId()));

            // اربط FK بدون SELECT كامل
            m.setPatient(patientRepository.getReferenceById(dto.getPatientId()));
        }

        if (dto.getCreationDate() != null) m.setCreationDate(dto.getCreationDate());
        if (dto.getAllergies() != null && !dto.getAllergies().isBlank()) m.setAllergies(dto.getAllergies().trim());
        if (dto.getChronicDiseases() != null && !dto.getChronicDiseases().isBlank()) m.setChronicDiseases(dto.getChronicDiseases().trim());
        if (dto.getSurgeries() != null && !dto.getSurgeries().isBlank()) m.setSurgeries(dto.getSurgeries().trim());
        if (dto.getNotes() != null) m.setNotes(dto.getNotes().isBlank() ? null : dto.getNotes().trim());

        // مع @Transactional، ما كاين حاجة لـ save()، ولكن إلى بغيتي:
        medicalRecordRepository.saveAndFlush(m);
        return toDTO(m);
    }




    public void delete(Long id) {
        medicalRecordRepository.deleteById(id);
    }
    private MedicalRecordDTO toDTO(MedicalRecord m) {
        return new MedicalRecordDTO(
                m.getIdMedical(),
                m.getAllergies(),
                m.getCreationDate(),
                m.getChronicDiseases(),
                m.getSurgeries(),
                m.getNotes()
        );
    }
    public List<MedicalRecord> getByPatientId(Long idPatient) {
        return medicalRecordRepository.findByPatient_IdPatient(idPatient);
    }

}
