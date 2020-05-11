package logic;

import domain.Fitness;
import domain.Sport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SportTest {
    private Sport fitness;

    @BeforeEach
    void setUp() {
        this.fitness = new Fitness();
    }

    //Precision between 10 millis because of inaccuracy
    @Test
    void startAndStopTimer_5seconds() throws InterruptedException {
        fitness.startSport();
        Thread.sleep(5000);
        fitness.stopSport();

        long actual = fitness.getSportedTime();

        assertTrue(4995 <= actual && actual <= 5005);
    }
}
