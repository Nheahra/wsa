package wsa.controllers;

import org.springframework.web.bind.annotation.*;
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

        switch (searchRadioInput) {
            case "All":
                //Missions needs to be implemented in the database
                //fix this sql statement
                sql = "SELECT * FROM Locations, RelicLocationAssociation, Relics, Missions";
                break;
            case "Relics":
                sql = "SELECT * FROM Relics WHERE RelicName LIKE '%" + userInput + "%';";
                break;
            case "Locations":
                sql = "RelicLocationAssociation";
                break;
            case "Parts":
                sql = "SELECT * FROM Relics WHERE " +
                        "Gold LIKE '%" + userInput + "%' OR " +
                        "Silver1 LIKE '%" + userInput + "%' OR " +
                        "Silver2 LIKE '%" + userInput + "%' OR " +
                        "Bronze1 LIKE '%" + userInput + "%' OR " +
                        "Bronze2 LIKE '%" + userInput + "%' OR " +
                        "Bronze3 LIKE '%" + userInput + "%';";
                break;
            case "Missions":
                sql = "";
                break;
        }

        return sqlExecution.getRelics(sql);

    }

    @CrossOrigin(origins = "https://wsa-kvarkonyi.c9users.io")
    @RequestMapping("/getQuality")
    public ArrayList<Quality> getQuality(@RequestParam(value="quality") String quality) throws Exception {
        sql = "SELECT * FROM Refinement WHERE Level = '" + quality + "';";
        return sqlExecution.getQuality(sql);
    }
}
