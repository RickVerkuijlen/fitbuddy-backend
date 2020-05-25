package controllers.sports;

import domain.Fitness;
import domain.ISportEssentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fitness")
@CrossOrigin(origins = "http://localhost:4200")
public class FitnessController extends SportEssentials {

    private Fitness fitnessLogic;

    @Autowired
    public FitnessController(Fitness fitnessLogic) {
        setRepository(fitnessLogic);
        this.fitnessLogic = fitnessLogic;
    }

    @GetMapping("/hello")
    public String hello() {
        return "FitnessController";
    }
}
