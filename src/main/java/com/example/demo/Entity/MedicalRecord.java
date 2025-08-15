package com.example.demo.Entity;

import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
@Entity

public class MedicalRecord {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long idMedical;

	    @NotBlank
	    private String allergies;
	    @NotNull
	    private LocalDate creationDate;
	    @NotBlank
	    private String chronicDiseases;
	    @NotBlank
	    private String surgeries;
	    @NotBlank
	    private String notes;

	    @OneToOne(optional = false)
		@JsonIgnore
	    private Patient patient;

}
