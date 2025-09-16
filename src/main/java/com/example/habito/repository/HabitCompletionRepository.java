package com.example.habito.repository;

import com.example.habito.model.HabitCompletion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HabitCompletionRepository extends JpaRepository<HabitCompletion,Long> {

    // Find all completions of a habit ordered by date
    List<HabitCompletion> findByHabitIdOrderByCompletionDateAsc(Long habitId);

    // Count how many times a habit has been completed
    long countByHabitId(Long habitId);

    // Check if a habit was completed on a specific date
    boolean existsByHabitIdAndCompletionDate(Long habitId, LocalDate completionDate);

    List<HabitCompletion> findByHabitId(Long habitId);

    Optional<HabitCompletion> findByHabitIdAndCompletionDate(Long habitId, LocalDate completionDate);
}
