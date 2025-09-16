package com.example.demo.DTO;

import java.time.LocalDateTime;

public class AppointmentDTO {
    private Long idAppoint;
    private Long patientId;
    private String fullName;
    private LocalDateTime dateTime;
    private String reason;

    public AppointmentDTO() {
    }

    public AppointmentDTO(Long idAppoint,Long patientId,String fullName, LocalDateTime dateTime, String reason) {
        this.idAppoint = idAppoint;
        this.patientId = patientId;
        this.fullName = fullName;
        this.dateTime = dateTime;
        this.reason = reason;
    }

    public Long getIdAppoint() { return idAppoint; }
    public void setIdAppoint(Long idAppoint) { this.idAppoint = idAppoint; }

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    // 🔁 توافق مع مفاتيح قديمة في الفرونت (اختياري ولكن مفيد)
    public String getPatientName() { return fullName; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}

