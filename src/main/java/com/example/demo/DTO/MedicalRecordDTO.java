package com.example.demo.DTO;

import java.time.LocalDate;

public class MedicalRecordDTO {
 private Long idMedical;
 private String allergies;
 private LocalDate creationDate;
 private String chronicDiseases;
 private String surgeries;
 private String notes;

 private String fullName; // New field for patient's full name

 public MedicalRecordDTO() {
 }

 public MedicalRecordDTO(Long idMedical, String allergies, LocalDate creationDate,
                         String chronicDiseases, String surgeries, String notes , String fullName) {
  this.idMedical = idMedical;
  this.allergies = allergies;
  this.creationDate = creationDate;
  this.chronicDiseases = chronicDiseases;
  this.surgeries = surgeries;
  this.notes = notes;
    this.fullName = fullName;
 }

 public long getIdMedical() {
  return idMedical;
 }

 public void setIdMedical(long idMedical) {
  this.idMedical = idMedical;
 }

 public LocalDate getCreationDate() {
  return creationDate;
 }

 public void setCreationDate(LocalDate creationDate) {
  this.creationDate = creationDate;
 }

 public String getAllergies() {
  return allergies;
 }

 public void setAllergies(String allergies) {
  this.allergies = allergies;
 }

 public String getChronicDiseases() {
  return chronicDiseases;
 }

 public void setChronicDiseases(String chronicDiseases) {
  this.chronicDiseases = chronicDiseases;
 }

 public String getSurgeries() {
  return surgeries;
 }

 public void setSurgeries(String surgeries) {
  this.surgeries = surgeries;
 }

 public String getNotes() {
  return notes;
 }

 public void setNotes(String notes) {
  this.notes = notes;
 }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
