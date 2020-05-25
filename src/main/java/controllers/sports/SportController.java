package controllers.sports;

import domain.Sport;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.SportRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sport")
@CrossOrigin(origins = "http://localhost:4200")
@Api(tags = "Sport Controller", description = "Controller to access all general sports data")
public class SportController {

    private SportRepository sportRepository;

    @Autowired
    public SportController(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody
    HttpEntity<Sport> getSportById(@PathVariable String id) {
        System.out.println("[" + LocalDateTime.now() + "] getSportById");
        return new ResponseEntity<>(sportRepository.getSportById(Integer.parseInt(id)), HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public @ResponseBody
    HttpEntity<List<Sport>> getAllSports() {
        System.out.println("[" + LocalDateTime.now() + "] getAllSports");

        return new ResponseEntity<>(sportRepository.getAllSports(), HttpStatus.OK);
    }

}
