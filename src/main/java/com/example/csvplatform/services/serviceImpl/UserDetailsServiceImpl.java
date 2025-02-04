package com.example.csvplatform.services.serviceImpl;

import com.example.csvplatform.entities.User;
import com.example.csvplatform.entities.Volunteer;
import com.example.csvplatform.repositories.UserRepository;
import com.example.csvplatform.repositories.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    private VolunteerRepository volunteerRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Fetching User with Email: " + email);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            System.out.println("No user foung");
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println("Fetching User Details");
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }

}
