package com.example.demo.Entity;




import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity

public class Consultation {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idConsul;
	@NotNull
    private LocalDate date;
	@NotBlank
    private String symptoms;
	@NotBlank
    private String diagnosis;
	@NotBlank
    private String notes;

    @ManyToOne
    @JsonIgnore
    private Patient patient;

    @OneToOne(mappedBy = "consultation")
    private Prescription prescription;


}
