package com.example.habito.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private String icon;

    @Getter @Setter
    private String color;

    @Getter @Setter
    private boolean archived = false;

    @Getter @Setter
    private LocalDateTime createdAt = LocalDateTime.now();

    @Getter @Setter
    private String reminderTime; // format HH:mm

    @ElementCollection
    @Getter @Setter
    private List<LocalDate> completions; // doneDates in frontend

    // Optional: add a convenience method to calculate streak if needed

}
