package com.example.demo.Entity;

import java.time.LocalDate;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

@Data
@Entity
public class Patient {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPatient;
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

    
    @OneToMany (mappedBy = "patient" , cascade = CascadeType.ALL)
    private List<MedicalRecord> medicalRecord;
    
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Consultation> consultations;

	





	


   

	

    
    
}
