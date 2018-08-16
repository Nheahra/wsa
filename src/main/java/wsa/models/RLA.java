package wsa.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class RLA {

    @Id
    @GeneratedValue
    private int id;
    private String rotation;
    @NotNull
    private int relicId;
    @NotNull
    private int locationId;
    @NotNull
    private String dropChance;

    public RLA () {}

    public RLA (int id, String rotation, int relicId, int locationId, String dropChance){
        this.id = id;
        this.rotation = rotation;
        this.relicId = relicId;
        this.locationId = locationId;
        this.dropChance = dropChance;
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
}
