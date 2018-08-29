package wsa.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//This class is only for GET methods
@Entity
//@Table(name = "Relics")
public class Relic {

    @Id
    @GeneratedValue
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String gold;
    @NotNull
    private String silver1;
    @NotNull
    private String silver2;
    @NotNull
    private String bronze1;
    @NotNull
    private String bronze2;
    @NotNull
    private String bronze3;
    @NotNull
    private int isAvailable;

    public Relic(){}

    public Relic(String name, String gold, String silver1, String silver2, String bronze1, String bronze2, String bronze3, int isAvailable){
        this.name = name;
        this.gold = gold;
        this.silver1 = silver1;
        this.silver2 = silver2;
        this.bronze1 = bronze1;
        this.bronze2 = bronze2;
        this.bronze3 = bronze3;
        this.isAvailable = isAvailable;
    }

    public int getId(){
        return id;
    }
    public void setId(int relicId) {
        this.id = relicId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getGold() {
        return gold;
    }
    public void setGold(String gold) {
        this.gold = gold;
    }

    public String getSilver1() {
        return silver1;
    }
    public void setSilver1(String silver1) {
        this.silver1 = silver1;
    }

    public String getSilver2() {
        return silver2;
    }
    public void setSilver2(String silver2) {
        this.silver2 = silver2;
    }

    public String getBronze1() {
        return bronze1;
    }
    public void setBronze1(String bronze1) {
        this.bronze1 = bronze1;
    }

    public String getBronze2() {
        return bronze2;
    }
    public void setBronze2(String bronze2) {
        this.bronze2 = bronze2;
    }

    public String getBronze3() {
        return bronze3;
    }
    public void setBronze3(String bronze3) {
        this.bronze3 = bronze3;
    }

    public int getIsAvailable() {
        return isAvailable;
    }
    public void setIsAvailable(int isAvailable) {
        this.isAvailable = isAvailable;
    }
}
