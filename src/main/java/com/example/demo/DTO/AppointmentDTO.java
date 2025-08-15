package com.example.demo.DTO;

import java.time.LocalDateTime;

public class AppointmentDTO {
    private long idAppoint;
    private LocalDateTime dateTime;
    private String reason;

    public AppointmentDTO() {
    }

    public AppointmentDTO(long idAppoint, LocalDateTime dateTime, String reason) {
        this.idAppoint = idAppoint;
        this.dateTime = dateTime;
        this.reason = reason;
    }

    public long getIdAppoint() {
        return idAppoint;
    }

    public void setIdAppoint(long idAppoint) {
        this.idAppoint = idAppoint;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

