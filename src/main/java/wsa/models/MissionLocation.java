package wsa.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class MissionLocation {

    @Id
    @GeneratedValue
    private int id;
    private String rotation;
    private int relicId;
    private int locationId;
    private String dropChance;
    private String planet;
    private String locationName;
    private String relicName;
    private String mission;

    public MissionLocation(){}

    public MissionLocation(int id, String rotation, int relicId, int locationId, String dropChance, String planet, String locationName, String relicName, String mission){
        this.id = id;
        this.rotation = rotation;
        this.relicId = relicId;
        this.locationId = locationId;
        this.dropChance = dropChance;
        this.planet = planet;
        this.locationName = locationName;
        this.relicName = relicName;
        this.mission = mission;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getRotation() {
        return rotation;
    }
    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    public int getRelicId() {
        return relicId;
    }
    public void setRelicId(int relicId) {
        this.relicId = relicId;
    }

    public int getLocationId() {
        return locationId;
    }
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getDropChance() {
        return dropChance;
    }
    public void setDropChance(String dropChance) {
        this.dropChance = dropChance;
    }

    public String getPlanet() {
        return planet;
    }
    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public String getLocationName() {
        return locationName;
    }
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getRelicName() {
        return relicName;
    }
    public void setRelicName(String relicName) {
        this.relicName = relicName;
    }

    public String getMission() {
        return mission;
    }
    public void setMission(String mission) {
        this.mission = mission;
    }
}
