package com.example.csvplatform.controller.api;

import com.example.csvplatform.services.FilteringServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RestController
@RequestMapping("/filter")
public class FilteringController {

    @Autowired
    private FilteringServices filteringServices;

    @GetMapping("/bySkills")
    public ResponseEntity<?> getTasksBySkills(@RequestParam List<String> skills) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(filteringServices.getTasksBySkills(skills));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to fetch data");
        }
    }

    @GetMapping("/byLocation")
    public ResponseEntity<?> getTaskByLocation(@RequestParam String location) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(filteringServices.getTaskByLocation(location));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to fetch data");
        }
    }

    @GetMapping("/byDate")
    public ResponseEntity<?> getTaskByDate (@RequestParam
                                                @DateTimeFormat(pattern = "dd-MM-yyyy")
                                                LocalDate date)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(filteringServices.getTaskByDate(date));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to fetch data");
        }
    }
}
