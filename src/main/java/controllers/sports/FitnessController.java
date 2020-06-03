package controllers.sports;

import domain.Fitness;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fitness")
@CrossOrigin(origins = "http://localhost:4200")
@Api(tags = "Fitness Controller", description = "Controller to access all Fitness related data")
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
