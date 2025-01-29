package com.example.csvplatform.controller.ui;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/volunteer")
public class volunteerUiController {

    @GetMapping("/home")
    public String volunteerHomeView (Model model) {
        return "/volunteer/home";
    }

    @GetMapping("/my_tasks")
    public String myTasksView (Model model) {
        return "/volunteer/my-tasks";
    }

    @GetMapping("/rating")
    public String ratingView (Model model) {
        return "/volunteer/rating";
    }

    @GetMapping("/search_filter")
    public String searchFilterView (Model model) {
        return "/volunteer/search-filter";
    }

    @GetMapping("/tasks")
    public String tasksView () {
        return "/volunteer/tasks";
    }



}
