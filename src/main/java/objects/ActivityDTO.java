package objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "activity")
public class ActivityDTO extends ResourceSupport implements Serializable {

    @Id
    @JsonIgnore
    public int activityId;

    @Column(name = "userid")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String userId;

    @Column(name = "sportid")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public int sportId;

    @Column(name = "date")
    public Date date;

    @Column(name = "timeSportedInSeconds")
    public double timeSportedInSeconds;

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTimeSportedInSeconds() {
        return timeSportedInSeconds;
    }

    public void setTimeSportedInSeconds(double timeSportedInSeconds) {
        this.timeSportedInSeconds = timeSportedInSeconds;
    }
}
