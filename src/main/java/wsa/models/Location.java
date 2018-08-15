package wsa.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Locations")
public class Location {

    @Id
    @GeneratedValue
    private int id;
    @NotNull
    private String locationName;
    @NotNull
    private String mission;
    @NotNull
    private String planet;

    public Location(){}

    public Location(String locationName, String mission, String planet){
        this.locationName = locationName;
        this.mission = mission;
        this.planet = planet;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getMission() {
        return mission;
    }
    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getPlanet() {
        return planet;
    }
    public void setPlanet(String planet) {
        this.planet = planet;
    }
}
