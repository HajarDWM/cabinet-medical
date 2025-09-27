package com.example.demo.Entity;

import java.time.LocalDate;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties; ///????????
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Patient {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPatient;
	@NotBlank
	private String fullName;
	@NotNull
    @Past
	private LocalDate birthDate;
	@NotBlank
    private String phone;
	@NotBlank
    private String address;
	@NotBlank
    private String maritalStatus;
	@NotBlank
    private String bloodType;
	
    private String notes;


    
    
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointment> appointments;


    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private MedicalRecord medicalRecord;
    
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Consultation> consultations;



}
