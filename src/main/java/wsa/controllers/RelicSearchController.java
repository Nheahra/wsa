package wsa.controllers;

import org.springframework.web.bind.annotation.*;
import wsa.models.MissionLocation;
import wsa.models.Quality;
import wsa.models.Relic;

import java.util.ArrayList;

@RestController
public class RelicSearchController {

    private SqlExecution sqlExecution = new SqlExecution();
    String sql;

    @CrossOrigin(origins = "https://wsa-kvarkonyi.c9users.io")
    @RequestMapping("/findRelics")
    public ArrayList<Relic> findRelics(@RequestParam(value="search") String searchRadioInput, @RequestParam(value="userInput") String userInput) throws Exception {
        int i = 1;
        switch (searchRadioInput) {
            case "Relics":
                sql = "SELECT * FROM Relics WHERE RelicName LIKE ?;";
                break;
            case "Parts":
                sql = "SELECT * FROM Relics WHERE " +
                        "Gold LIKE ? OR " +
                        "Silver1 LIKE ? OR " +
                        "Silver2 LIKE ? OR " +
                        "Bronze1 LIKE ? OR " +
                        "Bronze2 LIKE ? OR " +
                        "Bronze3 LIKE ?;";
                i = 6;
                break;
        }
        System.out.println(sqlExecution.getRelics(sql, i, userInput));
        return sqlExecution.getRelics(sql, i, userInput);

    }

    @CrossOrigin(origins = "https://wsa-kvarkonyi.c9users.io")
    @RequestMapping("/getQuality")
    public ArrayList<Quality> getQuality(@RequestParam(value="quality") String quality) throws Exception {
        System.out.println(quality);
        sql = "SELECT * FROM Refinement WHERE Level = '" + quality + "';";
        System.out.println(sqlExecution.getQuality(sql));
        return sqlExecution.getQuality(sql);
    }

    @CrossOrigin(origins = "https://wsa-kvarkonyi.c9users.io")
    @RequestMapping("/missionLocation")
    public ArrayList<MissionLocation> missionLocaion(@RequestParam(value="search") String searchRadioInput, @RequestParam(value="userInput") String userInput) throws Exception {
        int i = 1;
        switch (searchRadioInput) {
            case "Missions":
                sql = "SELECT rla.rlaID, rla.Rotation, rla.RelicID, rla.LocationID, rla.DropChance, loc.Planet, loc.LocationName, loc.Mission, rel.RelicId, rel.RelicName " +
                        "FROM warframeSearchApp.RelicLocationAssociation AS rla " +
                        "INNER JOIN warframeSearchApp.Locations AS loc ON rla.LocationID = loc.LocationID " +
                        "INNER JOIN warframeSearchApp.Relics AS rel ON rla.RelicID = rel.RelicId " +
                        "WHERE loc.Mission Like ?;";
                break;
            case "Locations":
                sql = "SELECT rla.rlaID, rla.Rotation, rla.RelicID, rla.LocationID, rla.DropChance, loc.Planet, loc.LocationName, loc.Mission, rel.RelicId, rel.RelicName " +
                        "FROM warframeSearchApp.RelicLocationAssociation AS rla " +
                        "INNER JOIN warframeSearchApp.Locations AS loc ON rla.LocationID = loc.LocationID " +
                        "INNER JOIN warframeSearchApp.Relics AS rel ON rla.RelicID = rel.RelicId " +
                        "WHERE loc.Planet Like ? " +
                        "OR loc.LocationName Like ?;";
                i = 2;
                break;
        }
        System.out.println(sqlExecution.getMissions(sql, i, userInput));
        return sqlExecution.getMissions(sql, i, userInput);

    }
}
