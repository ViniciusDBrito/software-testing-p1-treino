package br.ifsp.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkoutReportServiceTest {
    @Mock WorkoutRepository repo;
    @InjectMocks WorkoutReportService sut;

    @Test
    @DisplayName("Should return zero if UUID is not present")
    void shouldReturnZeroIfUuidIsNotPresent(){
        Member otherMember = new Member(UUID.randomUUID());
        final List<WorkoutSession> sessions = List.of(new WorkoutSession(otherMember, 10, 2.0));
        when(repo.findAll()).thenReturn(sessions);

        final double expected = sut.averageWorkoutPaidValue(UUID.randomUUID());
        assertThat(expected).isEqualTo(0.0);
    }

    @Test
    @DisplayName("Should calculate with only one session")
    void shouldCalculateWithOnlyOneSession(){
        final UUID memberUuid = UUID.randomUUID();
        final Member member = new Member(memberUuid);
        final List<WorkoutSession> sessions = List.of(new WorkoutSession(member, 10, 2.0));
        when(repo.findAll()).thenReturn(sessions);

        final double expected = sut.averageWorkoutPaidValue(UUID.randomUUID());
        assertThat(expected).isEqualTo(0.0);
    }

    @Test
    @DisplayName("Should calculate average with more than one session")
    void teste(){
        final UUID memberUuid = UUID.randomUUID();
        final Member member = new Member(memberUuid);
        final List<WorkoutSession> sessions = List.of(
                new WorkoutSession(member, 10, 2.0),
                new WorkoutSession(member, 10, 4.0),
                new WorkoutSession(new Member(UUID.randomUUID()), 10, 400.0));
        when(repo.findAll()).thenReturn(sessions);

        final double expected = sut.averageWorkoutPaidValue(UUID.randomUUID());
        assertThat(expected).isEqualTo(0.0);
        verify(repo, atLeast(1)).findAll();
    }

    @Test
    @DisplayName("Should not accept null UUID")
    void shouldNotAcceptNullUuid(){
        assertThatNullPointerException().isThrownBy( () -> sut.averageWorkoutPaidValue(null));
    }

}