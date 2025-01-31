package com.example.csvplatform.controller.ui;


import com.example.csvplatform.dtos.viewModels.RegisterViewModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@Slf4j
public class authUiController {

    private final RestClient restClient;

    @GetMapping("/")
    public String homeView (Model model) {

        return "home";
    }

    @GetMapping("/volunteerlogin")
    public String volunteerloginView () {
        return "volunteerlogin";
    }

    @GetMapping("/organisationlogin")
    public String organisationloginView () {
        return "organisationlogin";
    }

    @GetMapping("/register")
    public ModelAndView registerView () {
        ModelAndView mv = new ModelAndView("register","user",new RegisterViewModel());
        return mv;
    }

    @PostMapping("/register")
    public ModelAndView registerUser (@ModelAttribute RegisterViewModel model) {

        var response = restClient
                .post()
                .uri("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .body(model)
                .retrieve()
                .toEntity(String.class);

        ModelAndView mv =   new ModelAndView("login", "response", response);
        mv.addObject("register", new RegisterViewModel());
        mv.addObject("statusCode",response.getStatusCode());
        return mv;
    }

}
