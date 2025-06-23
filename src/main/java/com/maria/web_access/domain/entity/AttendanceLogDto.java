package com.maria.web_access.domain.entity;

import com.maria.web_access.domain.entity.AttendanceLog.EventType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AttendanceLogDto {
    private Integer id;
    private String username;
    private LocalDateTime eventTime;
    private EventType eventType;
    private String roomNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public AttendanceLogDto(Integer id, String username, LocalDateTime eventTime,
                            EventType eventType, String roomNumber) {
        this.id = id;
        this.username = username;
        this.eventTime = eventTime;
        this.eventType = eventType;
        this.roomNumber = roomNumber;
    }
    public String getFormattedEventTime() {
        if (eventTime != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm:ss");
            return eventTime.format(formatter);
        }
        return "";
    }
}