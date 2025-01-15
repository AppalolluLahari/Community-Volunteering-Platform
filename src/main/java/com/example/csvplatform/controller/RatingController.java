package com.example.csvplatform.controller;

import com.example.csvplatform.dtos.RatingDto;
import com.example.csvplatform.services.RatingServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    RatingServices ratingServices;

    @PostMapping("/rateVolunteer")
    public ResponseEntity<?> createRating(@RequestBody @Valid RatingDto ratingDto) {
        try {
            ratingServices.createRating(ratingDto);
            return ResponseEntity.ok("Rated Volunteer Successfully");
        } catch (Exception e) {
//            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to rate volunteer");
        }
    }

    @GetMapping("/deleteRating")
    public ResponseEntity<?> deleteRating(@PathVariable Integer ratingId) {
        try {
            ratingServices.deleteRating(ratingId);
            return ResponseEntity.ok("Deleted the Rating Successfully");
        } catch (Exception e) {
//            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An Error Occurred while deleting the rating");
        }
    }

    @PutMapping("/updateRating")
    public ResponseEntity<?> updateRating(@RequestBody @Valid RatingDto ratingDto) {
        try {
            ratingServices.updateRating(ratingDto);
            return ResponseEntity.ok("Updated the rating successfully");
        } catch (Exception e) {
//            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An Error Occurred while updating the rating");
        }
    }



}
