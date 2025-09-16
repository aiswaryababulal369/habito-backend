package com.example.habito.service;

import com.example.habito.model.HabitCompletion;
import com.example.habito.repository.HabitCompletionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HabitService {

    @Autowired
    private HabitCompletionRepository habitCompletionRepository;

    public int getCurrentStreak(Long habitId) {
        return habitCompletionRepository.findByHabitId(habitId).size();
    }

    public int getBestStreak(Long habitId) {
        List<HabitCompletion> completions = habitCompletionRepository.findByHabitId(habitId);

        if (completions.isEmpty()) return 0;

        List<LocalDate> sortedDates = completions.stream()
                .map(HabitCompletion::getCompletionDate)
                .distinct()
                .sorted()
                .toList();

        int best = 1;
        int current = 1;

        for (int i = 1; i < sortedDates.size(); i++) {
            LocalDate prev = sortedDates.get(i - 1);
            LocalDate curr = sortedDates.get(i);

            if (curr.equals(prev.plusDays(1))) {
                current++;
            } else {
                current = 1;
            }
            best = Math.max(best, current);
        }

        return best;
    }

}
