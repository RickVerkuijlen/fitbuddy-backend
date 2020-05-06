package repositories;

import context.SportContext;
import objects.SportDTO;
import objects.SubscribedSportDTO;
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

    public SportDTO getSportById(int id) {
        return sportContext.getSportById(id);
    }

    public List<SportDTO> getAllSports() {
        return sportContext.getAllSports();
    }
}
