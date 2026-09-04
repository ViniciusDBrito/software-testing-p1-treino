package br.ifsp.testing;

import java.util.List;

public interface WorkoutRepository {
    List<WorkoutSession> findAll();
}