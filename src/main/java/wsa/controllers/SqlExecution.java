package wsa.controllers;

import wsa.models.Location;
import wsa.models.Quality;
import wsa.models.Relic;

import java.sql.*;
import java.util.ArrayList;

class SqlExecution {

    private Connection con;
    private Statement st;
    private ResultSet resultSet = null;

    void pushData(String sql) throws Exception {

        try {
            con = DBConnect.getConnection();
            st = con.createStatement();
            st.execute(sql);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, st, null);
        }
    }

    int getRelicId(String sql) throws Exception{

        int relicId = 0;

        try {
            con = DBConnect.getConnection();
            Statement st = con.createStatement();
            resultSet = st.executeQuery(sql);

            while (resultSet.next()){
                relicId = resultSet.getInt("RelicId");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, st, null);
        }
        return relicId;
    }

    ArrayList<Relic> getRelics(String sql) throws Exception{

        ArrayList<Relic> relicSet = new ArrayList<>();

        try {
            con = DBConnect.getConnection();
            Statement st = con.createStatement();
            resultSet = st.executeQuery(sql);

            while (resultSet.next()){
                Relic relic = new Relic();

                relic.setId(resultSet.getInt("RelicId"));
                relic.setName(resultSet.getString("RelicName"));
                relic.setGold(resultSet.getString("Gold"));
                relic.setSilver1(resultSet.getString("Silver1"));
                relic.setSilver2(resultSet.getString("Silver2"));
                relic.setBronze1(resultSet.getString("Bronze1"));
                relic.setBronze2(resultSet.getString("Bronze2"));
                relic.setBronze3(resultSet.getString("Bronze3"));

                relicSet.add(relic);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, st, null);
        }

        return relicSet;
    }

    ArrayList<Quality> getQuality(String sql) throws Exception {

        ArrayList<Quality> qualityArray = new ArrayList<>();

        try {
            con = DBConnect.getConnection();
            Statement st = con.createStatement();
            resultSet = st.executeQuery(sql);

            while(resultSet.next()) {
                Quality quality = new Quality();

                quality.setId(resultSet.getInt("RefinementID"));
                quality.setLevel(resultSet.getString("Level"));
                quality.setBronze(resultSet.getString("BronzeChance"));
                quality.setSilver(resultSet.getString("SilverChance"));
                quality.setGold(resultSet.getString("GoldChance"));

                qualityArray.add(quality);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, st, null);
        }
        return qualityArray;
    }

    ArrayList<Location> getLocations(String sql) throws Exception {

        ArrayList<Location> locationsArray = new ArrayList<>();

        try{
            con = DBConnect.getConnection();
            Statement st = con.createStatement();
            resultSet = st.executeQuery(sql);

            while(resultSet.next()) {
                Location location = new Location();

                location.setId(resultSet.getInt("LocationID"));
                location.setLocationName(resultSet.getString("LocationName"));
                location.setMission((resultSet.getString("Mission")));
                location.setPlanet((resultSet.getString("Planet")));

                locationsArray.add(location);
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, st, null);
        }
        return locationsArray;
    }
}