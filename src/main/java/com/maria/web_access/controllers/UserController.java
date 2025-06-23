package com.maria.web_access.controllers;

import com.maria.web_access.domain.entity.Role;
import com.maria.web_access.domain.entity.User;
import com.maria.web_access.domain.entity.UserDetails;
import com.maria.web_access.domain.repo.UserRepo;
import com.maria.web_access.domain.service.EncryptionService;
import com.maria.web_access.domain.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@PreAuthorize("isAuthenticated()")
@Controller
@RequestMapping("/user")
class UserController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private EncryptionService encryptionService;

    @GetMapping
    public String userList(Model model, Authentication authentication) {
        List<User> users = userRepo.findAll();
        List<UserDetails> details = users.stream()
                .map(u -> userDetailsService.findByUser(u))
                .collect(Collectors.toList());

        // Создаем список для хранения расшифрованных данных
        List<Map<String, String>> decryptedData = new ArrayList<>();

        // Инициализируем пустыми значениями
        for (int i = 0; i < users.size(); i++) {
            decryptedData.add(new HashMap<>());
        }

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"));

        model.addAttribute("users", users);
        model.addAttribute("details", details);
        model.addAttribute("decryptedData", decryptedData);
        model.addAttribute("showDecrypted", false);
        model.addAttribute("isAdmin", isAdmin); // Добавлено в модель
        return "userList";
    }

    @GetMapping("{user}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user){
        user.setUsername(username);
        final Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()){
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(user);
        return "redirect:/user";
    }

    @PostMapping("/delete/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteUser(@PathVariable Long userId) {
        // Удаляем связанные записи
        userDetailsService.deleteByUserId(userId);
        userRepo.deleteById(userId);
        return "redirect:/user";
    }


    @PostMapping("/decrypt-all")
    public String decryptAllUsers(Model model) {
        List<User> users = userRepo.findAll();
        List<UserDetails> details = users.stream()
                .map(u -> userDetailsService.findByUser(u))
                .collect(Collectors.toList());

        // Расшифровываем данные для всех пользователей
        List<DecryptedUserData> decryptedData = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            UserDetails detail = details.get(i);

            String decryptedFullName = encryptionService.decrypt(detail.getFullName(), 28);
            String decryptedCardNumber = encryptionService.decrypt(detail.getCardNumber(), 11);

            decryptedData.add(new DecryptedUserData(
                    user.getId(),
                    decryptedFullName,
                    decryptedCardNumber
            ));
        }

        model.addAttribute("users", users);
        model.addAttribute("details", details);
        model.addAttribute("decryptedData", decryptedData);
        model.addAttribute("showDecrypted", true);

        return "userList";
    }

    // Внутренний класс для хранения расшифрованных данных
    public static class DecryptedUserData {
        private Long userId;
        private String fullName;
        private String cardNumber;

        public DecryptedUserData(Long userId, String fullName, String cardNumber) {
            this.userId = userId;
            this.fullName = fullName;
            this.cardNumber = cardNumber;
        }

        // Геттеры и сеттеры
        public Long getUserId() { return userId; }
        public String getFullName() { return fullName; }
        public String getCardNumber() { return cardNumber; }
    }


}