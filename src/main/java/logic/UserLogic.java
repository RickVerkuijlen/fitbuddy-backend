package logic;

import java.util.ArrayList;
import java.util.List;

public class UserLogic {
    private List<SportLogic> sports = new ArrayList<>();

    public UserLogic(){}

    public void addSport(SportLogic sport) {
        sports.add(sport);
    }

    public List<SportLogic> getSports() {
        return sports;
    }
}
