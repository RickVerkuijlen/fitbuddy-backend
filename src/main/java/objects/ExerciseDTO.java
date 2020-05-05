package objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ExerciseDTO {

    @Id
    private int id;
    private String description;
    private int reps;
    private double weight;
    private double timeInSeconds;
    private double kCalPerMinute;

    public ExerciseDTO() {}

    public String getDescription() {
        return description;
    }

    public int getReps() {
        return reps;
    }

    public double getWeight() {
        return weight;
    }

    public double getTimeInSeconds() {
        return timeInSeconds;
    }

    public double getkCalPerMinute() {
        return kCalPerMinute;
    }

    public void setkCalPerMinute(double kCalPerMinute) {
        this.kCalPerMinute = kCalPerMinute;
    }

    public void setTimeInSeconds(double timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }
}
