package com.maria.web_access.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping(value={"/sensors", "/journal"})
@PreAuthorize("hasAuthority('USER2')")
public class PhpController {


    @GetMapping("/redirectGraph")
    public String redirectBME() {
        return "redirect:http://localhost/dipivt/Send_graph_DB_ivt.php";
    }
    @GetMapping("/redirectGraphHouer")
    public String redirectBmeHour() {
        return "redirect:http://localhost/dipivt/Send_graph_DB_ivt_last_12_hours.php";
    }

    @GetMapping("/redirectGraph7Days")
    public String redirectBme7Days() {
        return "redirect:http://localhost/dipivt/Send_graph_DB_ivt_last_7_days.php";
    }

}
