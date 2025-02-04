package com.example.csvplatform.controller.ui;


import static com.example.csvplatform.configuration.ToastUtil.*;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestClient;
import org.springframework.web.servlet.ModelAndView;

import com.example.csvplatform.dtos.viewModels.LoginViewModel;
import com.example.csvplatform.dtos.viewModels.RegisterViewModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
    public ModelAndView volunteerloginView () {
        ModelAndView mv= new ModelAndView("volunteerlogin","login",new LoginViewModel());
        return mv;
    }

    @PostMapping("/volunteerlogin")
    public ModelAndView volunteerloginView(@ModelAttribute LoginViewModel model) {
         ModelAndView mv ;
        try {
            var response = restClient.post()
                    .uri("/volunteer_login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(model)
                    .retrieve()
                    .toEntity(String.class);

            ModelAndView mv1= new ModelAndView("volunteer/home");
            System.out.println(response);
            mv1.addObject("response", response);
            addToast(mv1, "Successfully LoggedIn", "success");
            return mv1;

        } catch (Exception e) {
            mv = new ModelAndView("volunteerlogin");
            mv.addObject("login",new LoginViewModel());
            addToast(mv, "Login attempt failed. Please try again.", "danger");
        }
        return mv;
    }

    @GetMapping("/organisationlogin")
    public ModelAndView organisationLoginView () {
        ModelAndView mv= new ModelAndView("organisationlogin","login",new LoginViewModel());
        return mv;
    }

    @PostMapping("/organisationlogin")
    public ModelAndView organisationLoginView(@ModelAttribute LoginViewModel model) {
        ModelAndView mv ;
        try {
            var response = restClient.post()
                    .uri("/organisation_login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(model)
                    .retrieve()
                    .toEntity(String.class);

            ModelAndView mv1= new ModelAndView("organisation/organisation-home");
            System.out.println(response);
            mv1.addObject("response", response);
            addToast(mv1, "Successfully LoggedIn", "success");
            return mv1;

        } catch (Exception e) {
            mv = new ModelAndView("organisationlogin");
            mv.addObject("login",new LoginViewModel());
            addToast(mv, "Login attempt failed. Please try again.", "danger");
        }
        return mv;
    }

    @GetMapping("/register")
    public ModelAndView registerView () {
        ModelAndView mv = new ModelAndView("register","user",new RegisterViewModel());
        return mv;
    }

//    @PostMapping("/register")
//    public ModelAndView registerUser (@ModelAttribute RegisterViewModel model) {
//
//        var response = restClient
//                .post()
//                .uri("/auth/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(model)
//                .retrieve()
//                .toEntity(String.class);
//
//        System.out.println(response);
//        ModelAndView mv =   new ModelAndView("home", "response", response);
//        mv.addObject("register", new RegisterViewModel());
//        mv.addObject("statusCode",response.getStatusCode());
//        mv.addObject("message", "Registration successful!");
//        return mv;
//    }
    @PostMapping("/register")
    public ModelAndView registerUser(@ModelAttribute RegisterViewModel model) {
        ModelAndView mv = new ModelAndView("home");
        try {
            var response = restClient.post()
                    .uri("/auth/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(model)
                    .retrieve()
                    .toEntity(String.class);

            System.out.println(response);
            mv.addObject("response", response);
            mv.addObject("statusCode", response.getStatusCode());
            addToast(mv, "Registration successful!", "success");

        } catch (Exception e) {
            addToast(mv, "Registration failed. Please try again.", "danger");
        }
        return mv;
    }

}
