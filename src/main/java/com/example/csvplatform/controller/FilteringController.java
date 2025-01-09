package com.example.csvplatform.controller;

import com.example.csvplatform.entities.Task;
import com.example.csvplatform.services.FilteringServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/filter")
public class FilteringController {

    @Autowired
    private FilteringServices filteringServices;

    @GetMapping("/bySkills")
    public List<Task> getTasksBySkills(@RequestParam List<String> skills) {
        return filteringServices.getTasksBySkills(skills);
    }
}
