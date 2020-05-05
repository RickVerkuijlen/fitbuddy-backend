package controllers.sports;

import logic.FitnessLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fitness")
@CrossOrigin(origins = "http://localhost:4200")
public class FitnessController extends SportEssentials {

    private FitnessLogic fitnessLogic;

    @Autowired
    public FitnessController(FitnessLogic fitnessLogic) {
        setRepository(fitnessLogic);
        this.fitnessLogic = fitnessLogic;
    }

    @GetMapping("/hello")
    public String hello() {
        return "FitnessController";
    }
}
