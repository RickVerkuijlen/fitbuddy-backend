package logic;

import objects.ExerciseDTO;
import objects.SportDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FitnessLogic extends SportLogic {
    private List<ExerciseLogic> scheme = new ArrayList<>();
    private UserLogic user;

    public FitnessLogic() {
    }

    @Override
    public void determinekCal() {
        for (ExerciseLogic exercise :
                scheme) {
            setkCal(exercise.getkCalPerMinute() * (exercise.getTimeInSeconds() / 60));
        }
    }

    public void createExercise(ExerciseDTO dto) {
        scheme.add(new ExerciseLogic(dto));
    }

    public List<ExerciseLogic> getScheme() {
        return this.scheme;
    }

    @Override
    public void startSport() {
        startTimer();
    }

    @Override
    public void stopSport() {
        stopTimer();
    }
}
