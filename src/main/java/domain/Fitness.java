package domain;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Component
public class Fitness extends Sport {

    private List<Exercise> scheme = new ArrayList<>();
    private double kCal;

    public void setkCal(double kCal) {
        this.kCal = kCal;
    }

    public double getkCal() {
        return kCal;
    }

    public void determinekCal() {
        for (Exercise exercise :
                scheme) {
            setkCal(exercise.getkCalPerMinute() * (exercise.getTimeInSeconds() / 60));
        }
    }

    public void createExercise(Exercise entity) {
        this.scheme.add(entity);
    }

    public List<Exercise> getScheme() {
        return scheme;
    }
}
