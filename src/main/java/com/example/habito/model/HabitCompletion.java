package com.example.habito.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class HabitCompletion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id", nullable = false)
    @Getter
    @Setter
    private Habit habit;

    @Getter
    @Setter
    private LocalDate completionDate;

    @Getter
    @Setter
    private Boolean isCompleted = true;

    @Getter
    @Setter
    private LocalDateTime createdAt = LocalDateTime.now();
}
