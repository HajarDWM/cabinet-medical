package com.example.demo.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity


public class Appointment {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private long idAppoint;
	 @NotNull
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
	 private LocalDateTime dateTime;
	 @NotBlank
	 private String reason;
	 
	 
	 @ManyToOne
	 @JoinColumn(name = "patient_id")
	 @JsonIgnore
	 private Patient patient;
	 
	

}
