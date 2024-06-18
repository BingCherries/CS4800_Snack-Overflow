package com.example.petallergytracker.Controller;

import com.example.petallergytracker.Models.AllergicReaction;
import com.example.petallergytracker.Service.AllergyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("allergyreport")
public class AllergyReportController {
    @Autowired
    private AllergyReportService allergyReportService;

    @GetMapping("/")
    public ResponseEntity<String> getAllergyReport() {
        String allergyReport = allergyReportService.generateAllergyReport();
        if (!allergyReport.isEmpty()) {
            return new ResponseEntity<String>(allergyReport, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }
}
