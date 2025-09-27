package com.example.demo.DTO;

public class PatientLiteDTO {
    private Long id;
    private String fullName;

    public PatientLiteDTO(Long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public Long getId() { return id; }
    public String getFullName() { return fullName; }
}
