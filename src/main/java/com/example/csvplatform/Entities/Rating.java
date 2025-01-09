package com.example.csvplatform.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
// import jakarta.validation.constraints.NotEmpty;
// import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rating")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private int ratingId;

    @Column(name = "task_id", nullable = false)
    private int taskId;

    @Column(name = "review", columnDefinition = "TEXT", nullable = false)
    private String review;

    @Column(name = "rating_score", nullable = false)
    private int ratingScore;

    @Column(name = "rated_to", nullable = false)
    private int ratedTo;

    @Column(name = "rated_by", nullable = false)
    private int ratedBy;
}
