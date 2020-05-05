package logic;

import objects.ExerciseDTO;

public class ExerciseLogic {
    private String description;
    private int reps;
    private double weight;
    private double timeInSeconds;
    private double kCalPerMinute;

    public ExerciseLogic() {

    }

    ExerciseLogic(ExerciseDTO dto) {
        this.description = dto.getDescription();
        this.reps = dto.getReps();
        this.weight = dto.getWeight();
        this.timeInSeconds = dto.getTimeInSeconds();
        this.kCalPerMinute = dto.getkCalPerMinute();
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
}
