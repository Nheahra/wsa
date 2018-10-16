package wsa.models;

public class Farm {

    private MissionLocation missionLocation;
    private Relic relic;

    public Farm(){}
    public Farm(MissionLocation missionLocation, Relic relic){
        this.missionLocation = missionLocation;
        this.relic = relic;
    }

    public MissionLocation getMissionLocation() {
        return missionLocation;
    }
    public void setMissionLocation(MissionLocation missionLocation) {
        this.missionLocation = missionLocation;
    }

    public Relic getRelic() {
        return relic;
    }
    public void setRelic(Relic relic) {
        this.relic = relic;
    }
}
