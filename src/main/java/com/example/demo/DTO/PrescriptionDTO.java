package com.example.demo.DTO;

import java.time.LocalDate;

public class PrescriptionDTO {

    private long idPerscription;
    private String medication;
    private String dosage;
    private String duration;
    private String notes;
    private Long consultationId;

    private LocalDate date;
    public PrescriptionDTO() {
    }
    public PrescriptionDTO(long idPerscription, String medication, String dosage, String duration, String
            notes,Long consultationId , LocalDate date) {
        this.idPerscription = idPerscription;
        this.medication = medication;
        this.dosage = dosage;
        this.duration = duration;
        this.notes = notes;
        this.consultationId = consultationId;
        this.date = date;
    }

    public long getIdPerscription() {
        return idPerscription;
    }

    public void setIdPerscription(long idPerscription) {
        this.idPerscription = idPerscription;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    public Long getConsultationId() {
        return consultationId;
    }
    public void setConsultationId(Long consultationId) {
        this.consultationId = consultationId;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }


}