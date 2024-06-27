package com.example.petallergytracker.Controller;

import com.example.petallergytracker.Models.DashboardData;
import com.example.petallergytracker.Service.UserDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserDashboardController {
    @Autowired
    private UserDashboardService userDashboardService;

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardData> getDashboardData() {
        DashboardData dashboardData = userDashboardService.getDashboardData();
        if (dashboardData != null) {
            return new ResponseEntity<DashboardData>(dashboardData, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
