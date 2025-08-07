package com.example.demo.DTO;



import java.time.LocalDate;

public class PatientDTO {
    private long idPatient;
    private String fullName;
    private LocalDate birthDate;
    private String phone;
    private String address;
    private String maritalStatus;
    private String bloodType;
    private String notes;

    public PatientDTO() {
    }

    public PatientDTO(String notes, String bloodType, String maritalStatus, String address, String phone, LocalDate birthDate, String fullName, long idPatient) {
        this.notes = notes;
        this.bloodType = bloodType;
        this.maritalStatus = maritalStatus;
        this.address = address;
        this.phone = phone;
        this.birthDate = birthDate;
        this.fullName = fullName;
        this.idPatient = idPatient;
    }

    public long getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(long idPatient) {
        this.idPatient = idPatient;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
