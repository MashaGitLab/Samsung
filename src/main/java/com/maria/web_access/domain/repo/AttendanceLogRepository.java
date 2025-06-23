package com.maria.web_access.domain.repo;

import com.maria.web_access.domain.entity.AttendanceLog;
import com.maria.web_access.domain.entity.AttendanceLogDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AttendanceLogRepository extends JpaRepository<AttendanceLog, Integer> {

    @Query("SELECT NEW com.maria.web_access.domain.entity.AttendanceLogDto(" +
            "a.id, u.username, a.eventTime, a.eventType, a.roomNumber) " +
            "FROM AttendanceLog a JOIN Usr u ON a.userId = u.id " +
            "ORDER BY a.eventTime DESC")
    List<AttendanceLogDto> findAllWithUsername();

    @Query("SELECT NEW com.maria.web_access.domain.entity.AttendanceLogDto(" +
            "a.id, u.username, a.eventTime, a.eventType, a.roomNumber) " +
            "FROM AttendanceLog a JOIN Usr u ON a.userId = u.id " +
            "WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :username, '%')) " +
            "ORDER BY a.eventTime DESC")
    List<AttendanceLogDto> findByUsernameContainingIgnoreCase(String username);

    // AttendanceLogRepository.java
    @Query("SELECT NEW com.maria.web_access.domain.entity.AttendanceLogDto(" +
            "a.id, u.username, a.eventTime, a.eventType, a.roomNumber) " +
            "FROM AttendanceLog a JOIN Usr u ON a.userId = u.id " +
            "WHERE u.username = :username " +
            "ORDER BY a.eventTime DESC")
    List<AttendanceLogDto> findByExactUsername(String username);

}