package domain;

import org.springframework.stereotype.Component;

@Component
public interface ISportEssentials {
    void startSport();
    void stopSport();
    long getSportedTime();
    boolean isSporting();
}
