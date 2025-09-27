package com.example.demo.DTO;

import java.time.LocalDate;

public class ConsultationDTO {
    private Long idConsul;
    private LocalDate date;
    private String symptoms;
    private String diagnosis;
    private String notes;

    private Long patientId; // For patient's ID
    private String fullName; // For patient's full name


    public ConsultationDTO() {
    }

    // Add this new constructor for the JPQL query
    public ConsultationDTO(Long idConsul, LocalDate date, String symptoms, String diagnosis, String notes,
                           Long patientId, String fullName) {
        this.idConsul = idConsul;
        this.date = date;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.notes = notes;
        this.patientId = patientId;
        this.fullName = fullName;
    }

    public ConsultationDTO(Long idConsul, LocalDate date, String symptoms, String diagnosis, String notes,
                           Long patientId, String medication, String fullName) {
        this.idConsul = idConsul;
        this.date = date;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.notes = notes;
        this.patientId = patientId;
        this.fullName = fullName;
    }


    public Long getIdConsul() {
        return idConsul;
    }

    public void setIdConsul(Long idConsul) {
        this.idConsul = idConsul;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }



  

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
