package com.example.csvplatform.services.serviceImpl;

import java.util.Optional;

import com.example.csvplatform.entities.Volunteer;
import com.example.csvplatform.repositories.UserRepository;
import com.example.csvplatform.repositories.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.csvplatform.dtos.RatingDto;
import com.example.csvplatform.entities.Rating;
import com.example.csvplatform.entities.Task;
import com.example.csvplatform.repositories.RatingRepository;
import com.example.csvplatform.repositories.TaskRepository;
import com.example.csvplatform.services.RatingServices;

@Service
public class RatingServiceImpl implements RatingServices {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private VolunteerRepository volunteerRepository;


    @Override
    public void createRating(RatingDto ratingDto) {
        // Verify task existence
        Task task = taskRepository.findById(ratingDto.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found"));

        Volunteer volunteer = volunteerRepository.findById(ratingDto.getRatedTo())
                .orElseThrow(() -> new RuntimeException("volunteer not found"));

        // Map DTO to Entity
        Rating rating = new Rating();
        rating.setTaskId(task.getTaskId());
        rating.setReview(ratingDto.getReview());
        rating.setRatingScore(ratingDto.getRatingScore());
        rating.setRatedTo(ratingDto.getRatedTo());
        rating.setRatedBy(ratingDto.getRatedBy());

        // Save the rating amd the volunteer changes
        ratingRepository.save(rating);

        volunteer.setRatingScore(ratingRepository.findAverageRatingByVolunteerId(ratingDto.getRatedTo()));
        volunteerRepository.save(volunteer);
    }

    @Override
    public void deleteRating(Integer ratingId) {
        if (ratingRepository.existsById(ratingId)) {
            ratingRepository.deleteById(ratingId);
        } else {
            throw new RuntimeException("Rating not found with ID: " + ratingId);
        }
    }

    @Override
    public void updateRating(RatingDto ratingDto) {
        Optional<Rating> existingRating = ratingRepository.findById(ratingDto.getRatingId());
        if (existingRating.isPresent()) {
            Rating updatedRating = existingRating.get();
            updatedRating.setReview(ratingDto.getReview());
            updatedRating.setRatingScore(ratingDto.getRatingScore());
            updatedRating.setRatedTo(ratingDto.getRatedTo());
            updatedRating.setRatedBy(ratingDto.getRatedBy());

            ratingRepository.save(updatedRating);
        } else {
            throw new RuntimeException("Rating not found with ID: " + ratingDto.getRatingId());
        }
    }
}