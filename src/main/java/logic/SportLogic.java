package logic;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public abstract class SportLogic implements ISportLogic {
    private long sportedTime;
    private Timestamp startedSport;
    private boolean isSporting = false;
    private double kCal;
    private long kCalPerMinute;

    public final void startTimer() {
        if(!isSporting) {
            isSporting = true;
            startedSport = new Timestamp(System.currentTimeMillis());
            sportedTime = 0;
        }
    }

    public final void stopTimer() {
        if(isSporting) {
            isSporting = false;
            sportedTime = System.currentTimeMillis() - startedSport.getTime();
        }
    }

    public final boolean isSporting() {
        return isSporting;
    }

    public final long getSportedTime() {
        return sportedTime;
    }

    public final double getkCal() {
        return kCal;
    }

    public void setkCal(double kCal) {
        this.kCal = kCal;
    }

    void determinekCal() {
        this.kCal = kCalPerMinute * millisToMinute(sportedTime);
    }

    private double millisToMinute(long millis) {
        return TimeUnit.MILLISECONDS.toMinutes(millis);
    }
}
