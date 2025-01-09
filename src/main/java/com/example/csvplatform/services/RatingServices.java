package com.example.csvplatform.services;

import com.example.csvplatform.dtos.RatingDto;
//import com.example.csvplatform.entities.Rating;

public interface RatingServices {
    void createRating(RatingDto ratingDto);
    void deleteRating(Integer rating_id);
    void updateRating(RatingDto ratingDto);
}
