package com.example.csvplatform.controller.ui;


import com.example.csvplatform.entities.Organisation;
import com.example.csvplatform.entities.Task;
import com.example.csvplatform.entities.User;
import com.example.csvplatform.entities.Volunteer;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClient;

import java.util.List;

@Controller
@RequestMapping("/organisation")
@RequiredArgsConstructor
@Slf4j
public class OrganisationUiController {

    private final RestClient restClient;

    @GetMapping("/home")
    public String organisationHomeView (Model model) {
        return "/organisation/organisation-home";
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
    public String myTasksView (Model model, HttpSession session) {
        try {
            Object obj = session.getAttribute("user");
            Organisation user = (Organisation) obj;

            System.out.println(user.getUserId());
            var tasks = restClient.get()
                    .uri("/task/getMyTasks/{user.getUserId()}")
                    .cookie("JSESSIONID", session.getId())
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<Task>>() {});
            System.out.println(tasks);
            model.addAttribute("tasks",tasks);

        } catch (Exception e) {
            model.addAttribute("error", "An error Occured while Fetching Data");
        }

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
        return "/organisation/completed-tasks";
    }

    @GetMapping("/top10volunteers")
    public String top10VolunteersView(Model model, HttpSession session) {

        try {
            // Fetching the top 10 volunteers (This could be from your service or an API)
            var volunteers = restClient.get()
                    .uri("/task/getVolunteers/top10")  // Update the URI accordingly
                    .cookie("JSESSIONID", session.getId())
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<Volunteer>>() {});  // Assuming Volunteer class exists
            System.out.println(volunteers);
            model.addAttribute("volunteers", volunteers);  // Adding volunteers to the model

        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while fetching data");
            e.printStackTrace();
        }

        // Returning the view for the top 10 volunteers
        return "/volunteer/top10volunteers";
    }
}
