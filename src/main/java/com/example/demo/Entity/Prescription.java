package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
@Entity

public class Prescription {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long idPerscription;
	    @NotBlank
	    private String medication;
	    @NotBlank
	    private String dosage;
	    @NotBlank
	    private String duration;
	    @NotBlank
	    private String notes;
	    
	  
	    
	    @OneToOne
	    @JoinColumn(name = "consultation_id")
	    private Consultation consultation;

}
