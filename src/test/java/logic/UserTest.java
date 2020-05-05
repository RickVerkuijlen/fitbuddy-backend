package logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private UserLogic user;

    @BeforeEach
    void setUp() {
        this.user = new UserLogic();
    }

    @Test
    void addSport_oneSport() {
        user.addSport(new FitnessLogic());

        int actual = user.getSports().size();
        int expected = 1;

        assertEquals(expected, actual);
    }

}
