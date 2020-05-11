package domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_sport")
public class SubscribedSport extends ResourceSupport implements Serializable{

    @Id
    @Column(name = "userid")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userUid;

    @Id
    @Column(name = "sportid")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int sportId;

    public SubscribedSport() {}

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }
}
