package com.example.csvplatform.controller.ui;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/organisation")
public class OrganisationUiController {

    @GetMapping("/home")
    public String organisationHomeView (Model model) {
        return "/organisation/home";
    }

    @GetMapping("/task_creation")
    public String tasksCreationView () {
        return "/organisation/taskCreation";
    }

    @GetMapping("/task_updation")
    public String tasksUpdationView () {
        return "/organisation/taskUpdation";
    }

    @GetMapping("/task_rating")
    public String tasksRatingsView () {
        return "/organisation/taskRating";
    }

    @GetMapping("/my_tasks")
    public String myTasksView (Model model) {
        return "/organisation/my-tasks";
    }

    @GetMapping("/my_ratings")
    public String myRatingsView (Model model) {
        return "/organisation/my-ratings";
    }

    @GetMapping("/edit_rating")
    public String edittRatingView (Model model) {
        return "/organisation/editRating";
    }

    @GetMapping("/completed_tasks")
    public String tasksCompletedView () {
        return "/organisation/completedTasks";
    }
}
