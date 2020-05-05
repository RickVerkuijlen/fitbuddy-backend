package logic;

import objects.ExerciseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FitnessTest {
    private FitnessLogic fitness;

    @BeforeEach
    void setUp() {
        this.fitness = new FitnessLogic();
        ExerciseDTO dto = new ExerciseDTO();
        dto.setkCalPerMinute(150);
        dto.setTimeInSeconds(120);
        this.fitness.createExercise(dto);
    }

    @Test
    public void addExercise_twoExercises() {
        fitness.createExercise(new ExerciseDTO());

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
