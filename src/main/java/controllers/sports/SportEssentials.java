package controllers.sports;

import domain.ISportEssentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public abstract class SportEssentials {

    private ISportEssentials sportLogic;
    private static final Logger log = LoggerFactory.getLogger(SportEssentials.class);

    void setRepository(ISportEssentials sportLogic){
        this.sportLogic = sportLogic;
    }

    @GetMapping("/startSport")
    public final @ResponseBody
    HttpEntity<Boolean> startSport() {
        log.info("StartSport");
        if(!sportLogic.isSporting()) {
            sportLogic.startSport();
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/stopSport")
    public final @ResponseBody
    HttpEntity<Boolean> stopSport() {
        log.info("StopSport");
        if(sportLogic.isSporting()) {
            sportLogic.stopSport();
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/sportTime")
    public final @ResponseBody
    HttpEntity<Long> getSportTime() {
        return new ResponseEntity<>(sportLogic.getSportedTime(), HttpStatus.OK);
    }
}
