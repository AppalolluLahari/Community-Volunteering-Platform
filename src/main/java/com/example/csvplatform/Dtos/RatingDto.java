package com.example.csvplatform.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingDto {

    private int ratingId;

    @NotNull(message = "Task ID cannot be null")
    private int taskId;

    @NotEmpty(message = "Review cannot be empty")
    private String review;

    @NotNull(message = "Rating score cannot be null")
    private int ratingScore;

    @NotNull(message = "Rated to cannot be null")
    private int ratedTo;

    @NotNull(message = "Rated by cannot be null")
    private int ratedBy;


}
