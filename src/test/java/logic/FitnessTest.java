package logic;

import domain.Exercise;
import domain.Fitness;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FitnessTest {
    private Fitness fitness;

    @BeforeEach
    void setUp() {
        this.fitness = new Fitness();
        Exercise dto = new Exercise();
        dto.setkCalPerMinute(150);
        dto.setTimeInSeconds(120);
        this.fitness.createExercise(dto);
    }

    @Test
    public void addExercise_twoExercises() {
        fitness.createExercise(new Exercise());

        int actual = fitness.getScheme().size();
        int expected = 2;

        assertEquals(expected, actual);
    }

    @Test
    void determinekCal_150kCalInTwoMinutes() {
        fitness.determinekCal();

        double actual = fitness.getkCal();
        double expected = 300;

        assertEquals(expected, actual);
    }
}
