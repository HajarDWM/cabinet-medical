package com.example.demo.DTO.Consultation;

import java.time.LocalDate;

public class ConsultationDTO {
    private Long idConsul;
    private LocalDate date;
    private String symptoms;
    private String diagnosis;
    private String notes;


    public ConsultationDTO() {
    }

    public ConsultationDTO(Long idConsul, LocalDate date, String symptoms, String diagnosis, String notes) {
        this.idConsul = idConsul;
        this.date = date;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.notes = notes;
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
}
