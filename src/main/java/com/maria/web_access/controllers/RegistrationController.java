package com.maria.web_access.controllers;

import com.maria.web_access.configы.XssSanitizer;
import com.maria.web_access.domain.entity.Role;
import com.maria.web_access.domain.entity.User;
import com.maria.web_access.domain.repo.SecretRepo;
import com.maria.web_access.domain.repo.UserRepo;
import com.maria.web_access.domain.service.EncryptionService;
import com.maria.web_access.domain.service.SecretService;
import com.maria.web_access.domain.service.UserDetailsService;
import com.maria.web_access.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SecretRepo secretRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private SecretService secretService;

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @Validated User user,
            BindingResult bindingResult,
            @RequestParam String secret,
            @RequestParam String fullName,
            @RequestParam String cardNumber,
            @RequestParam(required = false) String group,
            @RequestParam(required = false) String position,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        User userFromDB = userRepo.findByUsername( user.getUsername() );

        if (userFromDB != null){
            //model.addAttribute( "message", "Пользователь уже существует!" );
            return "registration";
        }

        if (!secretService.checkSecretExists(secret)) {
            model.addAttribute("error", "Неверный идентификатор");
            return "registration";
        }

        // Определяем тип пользователя
        boolean isStudent = secret.equals("1001");
        boolean isTeacher = secret.equals("1002");

        // Назначение ролей
        if (isStudent) {
            user.setRoles(Collections.singleton(Role.USER));
        } else if (isTeacher) {
            user.setRoles(Collections.singleton(Role.TEACHER));
        } else if (secret.equals("1003")) {
            user.setRoles(Collections.singleton(Role.ADMIN));
        }

        user.setActive(true);
        //user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(cardNumber.length() < 11)
        {
            int a = 11 - cardNumber.length();
            while (a != 0)
            {
            cardNumber += "_";
            a--;
            }
        }

        if(fullName.length() < 28)
        {
            int a = 28 - fullName.length();
            while (a != 0)
            {
                fullName += "_";
                a--;
            }
        }
        System.out.print("Len fullname = ");
        System.out.println(fullName);
        System.out.print("Len cardNumber = ");
        System.out.println(cardNumber);



        String encryptedCardNumber = encryptionService.encrypt(cardNumber, 11);

        String encryptedFullName = encryptionService.encrypt(fullName, 28);


        userService.saveUser(user);

        // Передаем булевы флаги вместо строк
        userDetailsService.saveUserDetails(
                user,
                encryptedFullName,
                encryptedCardNumber,
                group,
                position,
                isStudent,
                isTeacher
        );

        return "redirect:/user";
    }
}