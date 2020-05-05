package logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SportTest {
    private SportLogic fitness;

    @BeforeEach
    void setUp() {
        this.fitness = new FitnessLogic();
    }

    //Precision between 10 millis because of inaccuracy
    @Test
    void startAndStopTimer_5seconds() throws InterruptedException {
        fitness.startTimer();
        Thread.sleep(5000);
        fitness.stopTimer();

        long actual = fitness.getSportedTime();

        assertTrue(4995 <= actual && actual <= 5005);
    }
}
