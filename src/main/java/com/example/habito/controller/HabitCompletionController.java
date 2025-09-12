package com.example.habito.controller;

import com.example.habito.model.Habit;
import com.example.habito.model.HabitCompletion;
import com.example.habito.repository.HabitCompletionRepository;
import com.example.habito.repository.HabitRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8081", allowCredentials = "true")
@RestController
@RequestMapping("/api/habits/completions")
public class HabitCompletionController {

    private final HabitRepository habitRepository;
    private final HabitCompletionRepository habitCompletionRepository;

    public HabitCompletionController(HabitRepository habitRepository, HabitCompletionRepository habitCompletionRepository) {
        this.habitRepository = habitRepository;
        this.habitCompletionRepository = habitCompletionRepository;
    }

    @PostMapping("/{habitId}/completions")
    public HabitCompletion completeHabit(@PathVariable Long habitId) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new RuntimeException("Habit not found"));

        HabitCompletion completion = new HabitCompletion();
        completion.setHabit(habit);
        completion.setCompletionDate(LocalDate.now());
        completion.setIsCompleted(true);

        return habitCompletionRepository.save(completion);
    }

//    @GetMapping("/{habitId}/completions")
//    public List<HabitCompletion> getCompletions(@PathVariable Long habitId) {
//        return habitCompletionRepository.findByHabitId(habitId);
//    }

//    @GetMapping("/today")
//    public List<Map<String, Object>> getTodayHabits() {
//        LocalDate today = LocalDate.now();
//        return habitRepository.findAll().stream().map(habit -> {
//            boolean completed = habitCompletionRepository.existsByHabitIdAndCompletionDate(habit.getId(), today);
//            return Map.of("habitId", habit.getId(), "name", habit.getName(), "completed", completed);
//        }).toList();
//    }




}
