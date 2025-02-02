package com.example.csvplatform.services.serviceImpl;

import com.example.csvplatform.dtos.UserDto;
import com.example.csvplatform.dtos.VolunteerDetailsDTO;
import com.example.csvplatform.dtos.VolunteerDto;
import com.example.csvplatform.entities.*;
import com.example.csvplatform.repositories.OrganisationRepository;
import com.example.csvplatform.repositories.UserRepository;
import com.example.csvplatform.repositories.VolunteerRepository;
import com.example.csvplatform.repositories.VolunteerSkillsRepository;
import com.example.csvplatform.services.UserServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    VolunteerRepository volunteerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrganisationRepository organisationRepository;

    @Autowired
    VolunteerSkillsRepository volunteerSkillsRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserDto user) {
        if(("Volunteer").equalsIgnoreCase(user.getRole())) {
            Volunteer volunteer = new Volunteer();
            volunteer.setName(user.getName());
            volunteer.setEmail(user.getEmail());
            volunteer.setPhone(user.getPhone());
            volunteer.setPassword(passwordEncoder.encode(user.getPassword()));
            volunteer.setRole(user.getRole().toUpperCase());
            volunteer.setVerified(user.isVerified());
            volunteerRepository.save(volunteer);
        }
        else {
            Organisation organisation = new Organisation();
            organisation.setName(user.getName());
            organisation.setEmail(user.getEmail());
            organisation.setPhone(user.getPhone());
            organisation.setPassword(passwordEncoder.encode(user.getPassword()));
            organisation.setRole(user.getRole());
            organisation.setVerified(user.isVerified());
            organisationRepository.save(organisation);
        }
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getUsers () {
        return userRepository.findAll();
    }

    @Override
    public List<Organisation> getOrganisation() {
        return organisationRepository.findAll();
    }

    @Override
    @Transactional
    public void updateVolunteer(VolunteerDto volunteerDto, HttpSession session){
        Volunteer volunteer = volunteerRepository.findById(volunteerDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        volunteer.setName(volunteerDto.getName());
        volunteer.setLocation(volunteerDto.getLocation());
        volunteerRepository.save(volunteer);

        volunteerSkillsRepository.deleteByVolunteer_UserId(volunteer.getUserId());
        List<VolunteerSkills> skills = new ArrayList<>();
        for (String skillName : volunteerDto.getVolunteerSkills()) {
            VolunteerSkills skill = new VolunteerSkills();
            skill.setVolunteer(volunteer);
            skill.setSkillName(skillName);
            skills.add(skill);
        }
        volunteerSkillsRepository.saveAll(skills);
        volunteer = volunteerRepository.findById(volunteerDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        session.removeAttribute("user");
        session.setAttribute("user",volunteer);

    }

    @Override
    public List<VolunteerDetailsDTO> getTop10Volunteers() {
        Pageable pageable = PageRequest.of(0, 10);
        return volunteerRepository.findTop10ByRating(pageable);
    }

    @Override
    public List<VolunteerSkills> getVolunteerSkills(Integer id) {
        return volunteerSkillsRepository.findByUserId(id);
    }

}
