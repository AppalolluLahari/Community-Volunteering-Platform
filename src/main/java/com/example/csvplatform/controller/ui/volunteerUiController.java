package com.example.csvplatform.controller.ui;


import com.example.csvplatform.dtos.viewModels.LoginViewModel;
import com.example.csvplatform.dtos.viewModels.RegisterViewModel;
import com.example.csvplatform.dtos.viewModels.VolunteerUpdateViewModel;
import com.example.csvplatform.services.TaskServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.boot.Banner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

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
    public String ratingView (Model model) {
        return "/volunteer/rating";
    }

    @GetMapping("/search_filter")
    public String searchFilterView (Model model) {
        return "/volunteer/search-filter";
    }

    @GetMapping("/tasks")
    public String tasksView (Model model) {
        try {
            var tasks = restClient.get()
                    .uri("/task/getAllTasks")
                    .retrieve()
                    .toEntity(String.class);
            System.out.println(tasks);
            model.addAttribute("tasks",tasks);

        } catch (Exception e) {
            model.addAttribute("error", "An error Occured while Fetching Data");
        }
        return "/volunteer/tasks";
    }





}
