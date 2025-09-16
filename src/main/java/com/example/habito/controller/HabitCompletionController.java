package com.example.habito.controller;

import com.example.habito.model.Habit;
import com.example.habito.model.HabitCompletion;
import com.example.habito.repository.HabitCompletionRepository;
import com.example.habito.repository.HabitRepository;
import com.example.habito.service.HabitService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8081", allowCredentials = "true")
@RestController
@RequestMapping("/api/habits")
public class HabitCompletionController {

    private final HabitRepository habitRepository;
    private final HabitCompletionRepository habitCompletionRepository;
    private final HabitService habitService;

    public HabitCompletionController(HabitRepository habitRepository, HabitCompletionRepository habitCompletionRepository, HabitService habitService) {
        this.habitRepository = habitRepository;
        this.habitCompletionRepository = habitCompletionRepository;
        this.habitService = habitService;
    }

    @PostMapping("/{habitId}/completions")
    public HabitCompletion completeHabit(
            @PathVariable Long habitId,
            @RequestBody HabitCompletion request) {

        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new RuntimeException("Habit not found"));

        LocalDate completionDate = request.getCompletionDate();

        // Check if completion already exists
        HabitCompletion completion = habitCompletionRepository
                .findByHabitIdAndCompletionDate(habitId, completionDate)
                .orElse(new HabitCompletion());

        completion.setHabit(habit);
        completion.setCompletionDate(completionDate);
        completion.setIsCompleted(true);

        return habitCompletionRepository.save(completion);
    }

    @GetMapping("/{habitId}/completions")
    public List<HabitCompletion> getCompletions(@PathVariable Long habitId) {
        return habitCompletionRepository.findByHabitId(habitId);
    }

    @GetMapping("/{habitId}/streaks")
    public Map<String, Integer> getStreaks(@PathVariable Long habitId) {
        int currentStreak = habitService.getCurrentStreak(habitId);
        int bestStreak = habitService.getBestStreak(habitId);

        Map<String, Integer> response = new HashMap<>();
        response.put("currentStreak", currentStreak);
        response.put("bestStreak", bestStreak);

        return response;
    }

}
