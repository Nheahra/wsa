package wsa.controllers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

public class HtmlParser {

    private static HashMap<String,Element> tableMap = new HashMap<>();
    private static SqlExecution sqlExecution = new SqlExecution();

    public static void parseHtml() {
        Document doc;
        Element relicSibling;
        Elements headers;
        try {
            doc = Jsoup.connect("https://n8k6e2y6.ssl.hwcdn.net/repos/hnfvc0o3jnfvc873njb03enrf56.html").get();
            headers = doc.getElementsByTag("h3");
            for (Element header : headers) {
                String headerId = header.attr("id");
                if (headerId != "") {
                    relicSibling = header.nextElementSibling();
                    tableMap.put(headerId, relicSibling);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Delete the data in tables for a fresh parse
        try {
            sqlExecution.pushData("TRUNCATE TABLE Refinement;");
            //can't truncate a table with foreign keys, so delete and reset auto_increment
            sqlExecution.pushData("DELETE FROM Relics;");
            sqlExecution.pushData("ALTER TABLE Relics AUTO_INCREMENT = 1;");
            sqlExecution.pushData("DELETE FROM Locations");
            sqlExecution.pushData("ALTER TABLE Locations AUTO_INCREMENT = 1");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Makes a HashMap<RelicName, RelicDrops>
        Element relicRewards = tableMap.get("relicRewards");
        Element missionRewards = tableMap.get("missionRewards");

        try {
            relicParse(relicRewards);
            missionParse(missionRewards);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void relicParse(Element test) {
        String title = new String();
        HashMap<String, String> relicTest = new HashMap();
        HashMap<String, HashMap> multiRelic = new HashMap<>();

        for (Element tr : test.getElementsByTag("tr")){
            if (tr.hasClass("blank-row")){
                multiRelic.put(title, relicTest);
                title = "";
                relicTest = new HashMap();
            } else if (tr.child(0).is("th")){
                title = tr.child(0).text();
            } else {
                //concat string "." to get around double keys
                if(relicTest.containsKey(tr.child(0).text())){
                    //noinspection ResultOfMethodCallIgnored
                    relicTest.put(tr.child(0).text() + " ", tr.child(1).text());
                } else {
                    relicTest.put(tr.child(0).text(), tr.child(1).text());
                }
            }
        }

        //Refining the HashMap to push to SQL
        HashMap<String, HashMap<String, String>> refinementMap = new HashMap<>();
        ArrayList<String> relicNames = new ArrayList<>();

        for (HashMap.Entry<String, HashMap> item : multiRelic.entrySet()){

            ArrayList<String> percentageList = new ArrayList<>();
            ArrayList<String> totalPercents = new ArrayList<>();
            HashMap<String, String> sortedPercents = new HashMap<>();

            String[] relicName = item.getKey().split(" Relic [(]");
            relicName[1] = relicName[1].replace(")", "");

            HashMap<String, String> relicDrops = item.getValue();

            for(String drops : relicDrops.values()){
                //percentage[0] is unneeded (Uncommon, Uncommon, Uncommon, Rare, Uncommon, Uncommon)
                String[] percentage = drops.split("[(%]");
                String percentageFloat = percentage[1];
                totalPercents.add(percentageFloat);
                if (!percentageList.contains(percentageFloat)){
                    percentageList.add(percentageFloat);
                }
            }

            //sorting percentageList
            for(String percent : percentageList){
                int count = 0;
                for(String multiPercent : totalPercents){
                    if(percent.equals(multiPercent)){
                        count ++;
                    }
                }
                switch(count){
                    case 1: sortedPercents.put("gold", percent);
                    break;
                    case 2: sortedPercents.put("silver", percent);
                    break;
                    case 3: sortedPercents.put("bronze", percent);
                    break;
                    default:
                        System.out.println("Something went wrong when sorting percents.");
                }

            }
            if(!refinementMap.containsKey(relicName[1])){
                refinementMap.put(relicName[1], sortedPercents);
                try {
                    pushRefinement(relicName[1], sortedPercents);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            HashMap<String, String> sqlRelicMap = new HashMap<>();
            if (!relicNames.contains(relicName[0])) {
                relicNames.add(relicName[0]);
                int silverCounter = 1;
                int bronzeCounter = 1;
                for (HashMap.Entry<String, String> value : relicDrops.entrySet()) {

                    if (value.getValue().contains(sortedPercents.get("gold"))) {
                        sqlRelicMap.put("gold", value.getKey());
                    } else if (value.getValue().contains(sortedPercents.get("silver"))) {
                        sqlRelicMap.put("silver" + silverCounter, value.getKey());
                        silverCounter++;
                    } else if (value.getValue().contains(sortedPercents.get("bronze"))) {
                        sqlRelicMap.put("bronze" + bronzeCounter, value.getKey());
                        bronzeCounter++;
                    } else {
                        System.out.println("Key : Value pairs are not matching up.");
                    }
                }
            }

            if (!sqlRelicMap.isEmpty()) {
                try {
                    pushRelics(relicName[0], sqlRelicMap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static void pushRefinement(String name, HashMap<String, String> percents) throws Exception{
        sqlExecution.pushData("INSERT INTO Refinement (Level, BronzeChance, SilverChance, GoldChance) VALUES ('"
                + name + "', '"
                + percents.get("bronze") + "', '"
                + percents.get("silver") + "', '"
                + percents.get("gold") + "');"
        );
    }
    private static void pushRelics(String name, HashMap<String, String> drops) throws Exception{
        //the split() is to circumvent the double key issue stated on line 57
        sqlExecution.pushData("INSERT INTO Relics (RelicName, Gold, Silver1, Silver2, Bronze1, Bronze2, Bronze3) VALUES ('"
                + name + "', '"
                + drops.get("gold") + "', '"
                + drops.get("silver1") + "', '"
                + drops.get("silver2") + "', '"
                + drops.get("bronze1") + "', '"
                + drops.get("bronze2") + "', '"
                + drops.get("bronze3") + "');"
        );
    }
    static void missionParse(Element missionData){

        ArrayList<HashMap<String, String>> locationPush = new ArrayList<>();
        HashMap<String, String> locationTable = new HashMap<>();
        String locationName = "";
        String mission = "";
        String planet = "";
        String rotation = "";

        for (Element tr : missionData.getElementsByTag("tr")){
            if (tr.hasClass("blank-row")){
                locationTable.put("locationName", locationName);
                locationTable.put("mission", mission);
                locationTable.put("planet", planet);
                locationPush.add(locationTable);
                locationTable = new HashMap();
            } else if (tr.child(0).is("th")){
                if(tr.child(0).text().contains("Rotation")){
                    //assign rotation and relics
                } else {
                    String[] title = tr.child(0).text().split("/");
                    String[] nameMission = title[1].split(" [(]");
                    nameMission[1].replace(")", "");
                    planet = title[0];
                    locationName = nameMission[0];
                    mission = nameMission[1];
                }
            }
        }
        try {
            pushLocations(locationPush);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void pushLocations(ArrayList<HashMap<String, String>> locationPush) throws Exception{
        for (HashMap<String,String> location : locationPush) {
            sqlExecution.pushData("INSERT INTO Locations (LocationName, Mission, Planet) VALUES ('" +
                    location.get("locationName") + "','" +
                    location.get("mission") + "', '" +
                    location.get("planet") + "');"
            );
        }
    }
}
