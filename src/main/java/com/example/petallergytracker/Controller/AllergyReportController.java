package com.example.petallergytracker.Controller;

import com.example.petallergytracker.Models.AllergicReaction;
import com.example.petallergytracker.Service.AllergyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public class AllergyReportController {
    @Autowired
    private AllergyReportService allergyReportService;

    @GetMapping("/allergyreport")
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
