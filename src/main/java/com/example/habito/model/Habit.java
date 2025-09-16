package com.example.habito.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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





}
