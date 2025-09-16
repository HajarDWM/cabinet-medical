package com.example.demo.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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


	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "patient_id", referencedColumnName = "idPatient", nullable = true)
	@JsonIgnoreProperties({"appointments"}) // إلى كانت العلاقة راجعة من Patient→appointments
	private Patient patient;
	

}
