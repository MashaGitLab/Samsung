package com.maria.web_access.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @GetMapping("/")
    public String main_page(Model model) {
        return "main_page2";
    }


    /*

    @Autowired
    private TestEncService testEncService;

    @GetMapping("/")
    public String getMainPage(Model model) {
        List<Test_enc> encryptedData = testEncService.getLatestRecords();
        model.addAttribute("data", encryptedData);
        return "main"; // Возвращает имя представления (например, main.ftlh)
    }

    @GetMapping("/decrypt")
    public String decryptData(Model model) {
        List<Test_enc> decryptedData = testEncService.decryptAllData();
        model.addAttribute("data", decryptedData);
        return "main"; // Возвращает имя представления (например, main.ftlh)
    }


     */



}