package br.ifsp.testing;

import java.util.UUID;

public class WorkoutReportService {
    private final WorkoutRepository repository;

    public WorkoutReportService(WorkoutRepository repository) {
        this.repository = repository;
    }

    public double averageWorkoutPaidValue(UUID memberId) {
        if (memberId == null) throw new NullPointerException("Member id must not be null!");

        return repository.findAll().stream()
                .filter(ws -> ws.member().uuid().equals(memberId))
                .mapToDouble(WorkoutSession::totalCost)
                .average()
                .orElse(0.0);
    }
}