package com.example.csvplatform.controller.ui;


import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClient;
import org.springframework.web.servlet.ModelAndView;

import com.example.csvplatform.dtos.viewModels.VolunteerUpdateViewModel;
import com.example.csvplatform.entities.Task;
import com.example.csvplatform.entities.Volunteer;
import com.example.csvplatform.services.TaskServices;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/volunteer")
@RequiredArgsConstructor
@Slf4j
public class VolunteerUiController {
    private final RestClient restClient;

    private final TaskServices taskServices;



    @GetMapping("/home")
    public ModelAndView volunteerHomeView (@ModelAttribute VolunteerUpdateViewModel model) {
        ModelAndView mv = new ModelAndView("/volunteer/home","volunteerUpdateView",new VolunteerUpdateViewModel());
        return mv;
    }

    @GetMapping("/my_tasks")
    public String myTasksView (Model model) {
        return "/volunteer/my-tasks";
    }

    @GetMapping("/rating")
    public String ratingView ( ) {
        return "/volunteer/rating";
    }

    @GetMapping("/search_filter")
    public String searchFilterView (Model model) {
        return "/volunteer/search-filter";
    }



    @GetMapping("/alltasks")
    public String tasksView (Model model,HttpSession session) {

        try {
            var tasks = restClient.get()
                    .uri("/task/getAllTasks")
                    .cookie("JSESSIONID", session.getId())
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<Task>>() {});
            System.out.println(tasks);
            model.addAttribute("tasks",tasks);

        } catch (Exception e) {
            model.addAttribute("error", "An error Occured while Fetching Data");
        }
        return "/volunteer/tasks";
    }

    @PostMapping("/getTask")
    public String getTaskByTitleView(Model model, @RequestParam String title, HttpSession session) {
        try {
            // Fetch tasks by title using the service layer
            var tasks = restClient.get()
                    .uri("/task/getTask/"+title)
                    .cookie("JSESSIONID", session.getId())
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<Task>>() {});

            System.out.println(tasks);
            model.addAttribute("tasks", tasks);
        } catch (Exception e) {
            System.out.println(e);
            model.addAttribute("error", "An error occurred while fetching tasks"); // Add an error message
        }
        return "volunteer/tasks";
    }


    

    
    @GetMapping("/top10volunteers")
    public String top10VolunteersView(Model model, HttpSession session) {

        try {
            // Fetching the top 10 volunteers (This could be from your service or an API)
            var volunteers = restClient.get()
                    .uri("/volunteer/getTop10Volunteers")  // Update the URI accordingly
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
        return "/volunteer/top10volunteers";  // Path to the view template
    }
}





