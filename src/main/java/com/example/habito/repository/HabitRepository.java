package com.example.habito.repository;

import com.example.habito.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitRepository extends JpaRepository<Habit,Long> {
    long countByArchivedFalse();

}
