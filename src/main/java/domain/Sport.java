package domain;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "sport")
public class Sport extends ResourceSupport implements ISportEssentials {

    @Id
    @Column(name = "id")
    private int sportId;

    @Column(name = "name")
    private String name;

    @Column(name = "kcalperminute")
    private double kcalPerMinute;

    @Column(name = "description")
    private String description;

    @Transient
    private long sportedTime;

    @Transient
    private Timestamp startedSport;

    @Transient
    private boolean isSporting = false;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSportId() {
        return sportId;
    }

    public void getSportId(int id) {
        this.sportId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getKcalPerMinute() {
        return kcalPerMinute;
    }

    public void setKcalPerMinute(double kcalPerMinute) {
        this.kcalPerMinute = kcalPerMinute;
    }

    @Override
    public final void startSport() {
        if(!isSporting) {
            isSporting = true;
            startedSport = new Timestamp(System.currentTimeMillis());
            sportedTime = 0;
        }
    }

    @Override
    public final void stopSport() {
        if(isSporting) {
            isSporting = false;
            sportedTime = System.currentTimeMillis() - startedSport.getTime();
        }
    }

    @Override
    public long getSportedTime() {
        return sportedTime;
    }

    @Override
    public boolean isSporting() {
        return isSporting;
    }
}
