package com.example.csvplatform.controller.ui;


import com.example.csvplatform.dtos.viewModels.LoginViewModel;
import com.example.csvplatform.dtos.viewModels.RegisterViewModel;
import com.example.csvplatform.dtos.viewModels.VolunteerUpdateViewModel;
import com.example.csvplatform.entities.Task;
import com.example.csvplatform.services.TaskServices;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.boot.Banner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.example.csvplatform.configuration.ToastUtil.addToast;

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



}
