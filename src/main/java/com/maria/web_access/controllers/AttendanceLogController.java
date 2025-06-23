package com.maria.web_access.controllers;

import com.maria.web_access.configы.UserDetailsImpl;
import com.maria.web_access.domain.entity.*;
import com.maria.web_access.domain.service.AttendanceLogService;
import com.maria.web_access.domain.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER', 'USER')")
public class AttendanceLogController {

    private final AttendanceLogService logService;

    public AttendanceLogController(AttendanceLogService logService) {
        this.logService = logService;
    }

    // AttendanceLogController.java
    @GetMapping("/attendance")
    public String showAttendanceLog(
            @RequestParam(required = false) String username,
            Model model,
            Principal principal
    ) {
        List<AttendanceLogDto> logs;
        boolean isUser = false;

        if (principal != null) {
            // Получаем информацию о текущем пользователе
            Authentication authentication = (Authentication) principal;
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User currentUser = userDetails.getUser();

            // Проверяем роль USER
            isUser = currentUser.getRoles().contains(Role.USER);

            if (isUser) {
                // Для USER всегда используем его имя
                logs = logService.getLogsByExactUsername(currentUser.getUsername());
            } else {
                // Для других ролей учитываем параметр поиска
                logs = (username != null && !username.isEmpty())
                        ? logService.getLogsByUsername(username)
                        : logService.getAllLogs();
            }
        } else {
            // Обработка неаутентифицированных пользователей (если нужно)
            logs = List.of();
        }

        model.addAttribute("logs", logs);
        return "attendanceLog";
    }

}