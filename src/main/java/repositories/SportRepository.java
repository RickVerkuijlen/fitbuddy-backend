package repositories;

import context.SportContext;
import domain.Sport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SportRepository {

    private SportContext sportContext;

    @Autowired
    public SportRepository(SportContext sportContext) {
        this.sportContext = sportContext;
    }

    public Sport getSportById(int id) {
        return sportContext.getSportById(id);
    }

    public List<Sport> getAllSports() {
        return sportContext.getAllSports();
    }
}
