package wsa.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Refinement")
public class Quality {

    @Id
    @GeneratedValue
    private int id;
    @NotNull
    private String level;
    @NotNull
    private String bronze;
    @NotNull
    private String silver;
    @NotNull
    private String gold;

    public Quality () {}

    public Quality (String level, String bronze, String silver, String gold){
        this.level = level;
        this.bronze = bronze;
        this.silver = silver;
        this.gold = gold;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }

    public String getBronze() {
        return bronze;
    }
    public void setBronze(String bronze) {
        this.bronze = bronze;
    }

    public String getSilver() {
        return silver;
    }
    public void setSilver(String silver) {
        this.silver = silver;
    }

    public String getGold() {
        return gold;
    }
    public void setGold(String gold) {
        this.gold = gold;
    }
}
