package com.maria.web_access.controllers;

import com.maria.web_access.domain.entity.Journal;
import com.maria.web_access.domain.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
//@RequestMapping(value={"/sensors", "/journal"})
@PreAuthorize("hasAuthority('ADMIN')")
public class UserSensorsController {
    @Autowired
    private DataService dataService;

    /*
    @GetMapping("/sensors")
    public String getData(Model model, @RequestParam(required = false) Boolean showDetails) {
        // Получение последних данных из sensors_data
        List<SensorsData> sensorDataList = sensorsDataService.getLatestSensorData();

        if (!sensorDataList.isEmpty()) {
            SensorsData sensorData = sensorDataList.get(0);
            model.addAttribute("temperature", sensorData.getTemp());
            model.addAttribute("humidity", sensorData.getHum());
            model.addAttribute("pressure", sensorData.getPress());
            model.addAttribute("gas", sensorData.getGas());
            model.addAttribute("dateTime", sensorData.getTime());

            // Определение состояния на основе значения gas в процентах
            int airPurityPercentage = sensorData.getGas();
            String condition;

            if (airPurityPercentage >= 90) { // Соответствует 0-50 IAQ
                condition = "хорошо";
            } else if (airPurityPercentage >= 80 && airPurityPercentage < 90) { // Соответствует 51-100 IAQ
                condition = "средне";
            } else if (airPurityPercentage >= 70 && airPurityPercentage < 80) { // Соответствует 101-150 IAQ
                condition = "не очень";
            } else if (airPurityPercentage >= 60 && airPurityPercentage < 70) { // Соответствует 151-200 IAQ
                condition = "плохо";
            } else if (airPurityPercentage >= 40 && airPurityPercentage < 60) { // Соответствует 201-300 IAQ
                condition = "очень плохо";
            } else { // Соответствует 301-500 IAQ
                condition = "ужасно";
            }
            model.addAttribute("condition", condition);
            model.addAttribute("airPurityPercentage", airPurityPercentage); // Добавление процента чистоты воздуха в модель

        }


            // Получение данных о посте
            Locations locations = locationsService.getDeviceById(1); // Предполагается, что id поста = 1
            if (locations != null) {
                model.addAttribute("postName", locations.getName());
                model.addAttribute("latitude", locations.getLatitude());
                model.addAttribute("longitude", locations.getLongitude());
                model.addAttribute("address", locations.getAddress());
            }

        return "sensors";
}


     */
    @GetMapping("/journal")
    public String journal(Model model) {
        List<Journal> records = dataService.getLatestRecords();
        model.addAttribute("records", records);
        return "journal";
    }
}
