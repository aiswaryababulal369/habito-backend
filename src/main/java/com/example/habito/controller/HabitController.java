package com.example.habito.controller;

import com.example.habito.model.Habit;
import com.example.habito.model.HabitCompletion;
import com.example.habito.repository.HabitRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081", allowCredentials = "true")
@RestController
@RequestMapping("/api/habits")
public class HabitController {
    private final HabitRepository habitRepository;

    public HabitController(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    @GetMapping
    public List<Habit> getAllHabits() {
        return habitRepository.findAll();
    }

    @PostMapping
    public Habit addHabit(@RequestBody Habit habit) {
        return habitRepository.save(habit);
    }

    @PutMapping("/{id}")
    public Habit updateHabit(@PathVariable Long id, @RequestBody Habit updatedHabit) {
        return habitRepository.findById(id).map(habit -> {
            habit.setName(updatedHabit.getName());
            habit.setDescription(updatedHabit.getDescription());
            habit.setIcon(updatedHabit.getIcon());
            habit.setColor(updatedHabit.getColor());
            habit.setArchived(updatedHabit.isArchived());
            habit.setReminderTime(updatedHabit.getReminderTime());
            return habitRepository.save(habit);
        }).orElseThrow(() -> new RuntimeException("Habit not found"));
    }

    @DeleteMapping("/{id}")
    public void deleteHabit(@PathVariable Long id) {
        habitRepository.deleteById(id);
    }

    @GetMapping("/active/count")
    public long getActiveHabitsCount() {
        return habitRepository.countByArchivedFalse();
    }


}
