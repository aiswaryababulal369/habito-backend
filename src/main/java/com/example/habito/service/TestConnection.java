package com.example.habito.service;

import com.example.habito.model.Habit;
import com.example.habito.repository.HabitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class TestConnection implements CommandLineRunner {
    private final HabitRepository habitRepository;

    public TestConnection(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    @Override
    public void run(String... args) {
        Habit habit = new Habit();
        habit.setName("Drink Water");
        habit.setArchived(false);
        habit.setDescription("Stay hydrated by drinking at least 8 glasses of water daily.");
        habitRepository.save(habit);

        System.out.println("✅ Habit saved! Total habits: " + habitRepository.count());
    }
}
