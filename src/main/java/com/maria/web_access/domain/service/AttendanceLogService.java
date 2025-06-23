package com.maria.web_access.domain.service;

import com.maria.web_access.domain.entity.AttendanceLogDto;
import com.maria.web_access.domain.repo.AttendanceLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceLogService {

    private final AttendanceLogRepository repository;

    public AttendanceLogService(AttendanceLogRepository repository) {
        this.repository = repository;
    }

    public List<AttendanceLogDto> getAllLogs() {
        return repository.findAllWithUsername();
    }

    public List<AttendanceLogDto> getLogsByUsername(String username) {
        return repository.findByUsernameContainingIgnoreCase(username);
    }

    // AttendanceLogService.java
    public List<AttendanceLogDto> getLogsByExactUsername(String username) {
        return repository.findByExactUsername(username);
    }
}
