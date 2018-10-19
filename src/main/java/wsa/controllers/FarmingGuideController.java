package wsa.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wsa.models.Farm;
import wsa.models.Prime;

import java.util.ArrayList;

@RestController
public class FarmingGuideController {

    private SqlExecution sqlExecution = new SqlExecution();
    String sql;

    @CrossOrigin(origins = "https://wsa-kvarkonyi.c9users.io")
    @RequestMapping("/farmingGuide")
    public ArrayList<Farm> farmingGuide(@RequestParam(value="keyword") String keyword) throws Exception {
        //join the tables between primes, relics and missionLocation
        sql = "SELECT rla.rlaID, rla.Rotation, rla.RelicID, rla.LocationID, rla.DropChance, " +
                "loc.Planet, loc.LocationName, loc.Mission, " +
                "rel.RelicId, rel.RelicName, rel.Gold, rel.Silver1, rel.Silver2, rel.Bronze1, rel.Bronze2, rel.Bronze3 " +
                "FROM warframeSearchApp.Relics AS rel " +
                "INNER JOIN warframeSearchApp.RelicLocationAssociation AS rla ON rel.RelicId = rla.RelicID " +
                "INNER JOIN warframeSearchApp.Locations AS loc ON rla.LocationID = loc.LocationID " +
                "WHERE rel.Gold LIKE ? " +
                "OR rel.Silver1 LIKE ? " +
                "OR rel.Silver2 LIKE ? " +
                "OR rel.Bronze1 LIKE ? " +
                "OR rel.Bronze2 LIKE ? " +
                "OR rel.Bronze3 LIKE ?;";
        return sqlExecution.getFarm(sql, keyword);
    }

    @CrossOrigin(origins = "https://wsa-kvarkonyi.c9users.io")
    @RequestMapping("/primeList")
    public ArrayList<Prime> primeList() throws Exception {
        sql = "SELECT * FROM Primes;";
        return sqlExecution.getPrime(sql);
    }
}
