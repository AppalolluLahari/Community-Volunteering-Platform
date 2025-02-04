package com.example.csvplatform.controller.ui;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClient;

import com.example.csvplatform.entities.Organisation;
import com.example.csvplatform.entities.Task;
import com.example.csvplatform.entities.Volunteer;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/organisation")
@RequiredArgsConstructor
@Slf4j
public class OrganisationUiController {

    private final RestClient restClient;

    @GetMapping("/home")
    public String organisationHomeView(Model model) {
        return "/organisation/organisation-home";
    }

    @GetMapping("/task_creation")
    public String tasksCreationView(Model model) {
        model.addAttribute("task", new Task()); // Add empty task object for form binding
        return "/organisation/taskCreation";
    }

    @GetMapping("/task_updation")
    public String tasksUpdationView() {
        return "/organisation/taskUpdation";
    }

    @GetMapping("/task_rating")
    public String tasksRatingsView() {
        return "/organisation/taskRating";
    }

    @GetMapping("/my_tasks")
    public String myTasksView(Model model, HttpSession session) {
        try {
            Object obj = session.getAttribute("user");
            Organisation user = (Organisation) obj;

            // Fetching the tasks associated with the logged-in organisation
            var tasks = restClient.get()
                    .uri("/task/getMyTasks/{userId}", user.getUserId()) // Pass user ID correctly
                    .cookie("JSESSIONID", session.getId())
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<Task>>() {});

            model.addAttribute("tasks", tasks);
            model.addAttribute("task", new Task()); // Add empty task object for the form
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while fetching tasks");
        }

        return "/organisation/my-tasks";
    }

    @GetMapping("/my_ratings")
    public String myRatingsView(Model model) {
        return "/organisation/my-ratings";
    }

    @GetMapping("/edit_rating")
    public String editRatingView(Model model) {
        return "/organisation/editRating";
    }

    @GetMapping("/completed_tasks")
    public String tasksCompletedView() {
        return "/organisation/completed-tasks";
    }

    @GetMapping("/rating/top_volunteers")
    public String topVolunteersView(Model model, HttpSession session) {
        try {
            // Fetch the top volunteers from the service or via the REST client
            var volunteers = restClient.get()
                    .uri("/auth/getVolunteers/top10")  // Update the URI if needed
                    .cookie("JSESSIONID", session.getId())
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<Volunteer>>() {});  // Assuming Volunteer class exists
    
            System.out.println(volunteers);
            model.addAttribute("volunteers", volunteers);  // Add the list of volunteers to the model
    
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while fetching data");
            e.printStackTrace();
        }
    
        // Return the view for the rating page
        return "organisation/top-volunteers";  // Adjust the view name to match your actual template location
    }
    

    @PostMapping("/my_tasks")
public String createNewTask(@ModelAttribute Task task, HttpSession session, Model model) {
    try {
        // Retrieve Organisation object from session
        Object obj = session.getAttribute("user");
        Organisation user = (Organisation) obj;

        // Send the task creation request to the backend API
        ResponseEntity<Task> response = restClient.post()
                .uri("/task/create")
                .cookie("JSESSIONID", session.getId())
                .contentType(MediaType.APPLICATION_JSON) // Ensure JSON content type
                .body(task)  // Correctly sending the task object
                .retrieve()
                .toEntity(Task.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:/organisation/my_tasks"; // Redirect to My Tasks after successful creation
        } else {
            model.addAttribute("error", "Task creation failed. Please try again.");
            return "redirect:/organisation/task_creation?error=true"; 
        }
    } catch (Exception e) {
        e.printStackTrace();
        model.addAttribute("error", "An error occurred during task creation.");
        return "redirect:/organisation/task_creation?error=true"; 
    }
}

@PostMapping("/my_tasks/taskUpdate")
public String updateTask(@ModelAttribute Task task, HttpSession session, Model model) {
    try {
        // Retrieve Organisation object from session
        Object obj = session.getAttribute("user");
        Organisation user = (Organisation) obj;

        // Send the task update request to the backend API
        ResponseEntity<Task> response = restClient.post()
                .uri("/task/update")
                .cookie("JSESSIONID", session.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .body(task)
                .retrieve()
                .toEntity(Task.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:/organisation/my_tasks"; // Redirect to My Tasks after successful update
        } else {
            model.addAttribute("error", "Task update failed. Please try again.");
            return "redirect:/organisation/my_tasks?error=true";
        }
    } catch (Exception e) {
        e.printStackTrace();
        model.addAttribute("error", "An error occurred during task update.");
        return "redirect:/organisation/my_tasks?error=true";
    }
}




}
