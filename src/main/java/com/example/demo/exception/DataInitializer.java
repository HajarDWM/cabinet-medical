package com.example.demo.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.Entity.Appointment;
import com.example.demo.Entity.Consultation;
import com.example.demo.Entity.MedicalRecord;
import com.example.demo.Entity.Patient;
import com.example.demo.Entity.Prescription;
import com.example.demo.Repository.AppointmentRepository;
import com.example.demo.Repository.ConsultationRepository;
import com.example.demo.Repository.MedicalRecordRepository;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.Repository.PrescriptionRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Override
    public void run(String... args) {
        if (patientRepository.count() == 0) {
            Patient p1 = new Patient();
            p1.setFullName("Amina Zahra");
            p1.setBirthDate(LocalDate.of(1990, 5, 12));
            p1.setPhone("0600000000");
            p1.setAddress("Hay El Fath");
            p1.setMaritalStatus("Single");
            p1.setBloodType("O+");
            p1.setNotes("No known allergies");

            Patient p2 = new Patient();
            p2.setFullName("Fatima Bouchra");
            p2.setBirthDate(LocalDate.of(1985, 3, 22));
            p2.setPhone("0611111111");
            p2.setAddress("Hay Salam");
            p2.setMaritalStatus("Married");
            p2.setBloodType("A+");
            p2.setNotes("Allergic to penicillin");

            patientRepository.saveAll(List.of(p1, p2));
        }

        List<Patient> patients = patientRepository.findAll();

        if (!patients.isEmpty()) {
            Patient patient1 = patients.get(0);
            Patient patient2 = patients.get(1);

            // ➕ Appointment
            if (appointmentRepository.count() == 0) {
                Appointment appt = new Appointment();
                appt.setDateTime(LocalDateTime.now().plusDays(1));
                appt.setReason("Routine check");
                appt.setPatient(patient1);
                appointmentRepository.save(appt);
            }

            // ➕ Medical Record
            if (medicalRecordRepository.count() == 0) {
            	MedicalRecord record = new MedicalRecord();
                record.setCreationDate(LocalDate.now());
                record.setAllergies("Penicillin");
                record.setChronicDiseases("Diabetes");
                record.setSurgeries("Appendectomy");
                record.setNotes("Needs regular follow-up");
                record.setPatient(patient1);

                medicalRecordRepository.save(record);
            }

            // ➕ Consultation & Prescription
            if (consultationRepository.count() == 0 && prescriptionRepository.count() == 0) {
                Consultation consult = new Consultation();
                consult.setDate(LocalDate.now());
                consult.setSymptoms("Headache");
                consult.setDiagnosis("Tension");
                consult.setNotes("Need rest");
                consult.setPatient(patient2);
                consultationRepository.save(consult);

                Prescription pr1 = new Prescription();
                pr1.setMedication("Paracetamol");
                pr1.setDosage("500mg");
                pr1.setDuration("5 days");
                pr1.setNotes("After meals");
                pr1.setConsultation(consult);
                prescriptionRepository.save(pr1);

                
                
            }
    
            System.out.println("✅ Demo data inserted successfully.");
        }
    }
}
