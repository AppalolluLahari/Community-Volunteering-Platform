package com.example.csvplatform.configuration;

import com.example.csvplatform.entities.Organisation;
import com.example.csvplatform.entities.Volunteer;
import com.example.csvplatform.repositories.OrganisationRepository;
import com.example.csvplatform.repositories.VolunteerRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    public  VolunteerRepository volunteerRepository;

    @Autowired
    public OrganisationRepository organisationRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername(); // Assuming email is the username

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // Check if the user is a Volunteer
        if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_VOLUNTEER"))) {
            Volunteer volunteer = volunteerRepository.findByEmail(email);
            System.out.println("Authenticated Email: " + email);
            System.out.println("Authorities: " + authorities);
            if (volunteer != null) {
                session.setAttribute("user", volunteer);
            }
            response.sendRedirect("/volunteer/home");
        }
        // Check if the user is an Organization
        else if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_organization"))) {
            Organisation organization = organisationRepository.findByEmail(email);
            System.out.println("Authenticated Email: " + email);
            System.out.println("Authorities: " + authorities);
            if (organization != null) {
                session.setAttribute("user", organization);
            }
            response.sendRedirect("/organisation/home");
        }
        // Default redirect if no role matches
        else {
            response.sendRedirect("/home");
        }
    }
}