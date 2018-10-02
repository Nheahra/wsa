package wsa.controllers;

import wsa.models.*;

import java.sql.*;
import java.util.ArrayList;

class SqlExecution {

    private Connection con;
    private Statement st;
    private PreparedStatement pst;
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

    ArrayList<Relic> getRelics(String sql, int i, String userInput) throws Exception{

        ArrayList<Relic> relicSet = new ArrayList<>();
        int x = 1;

        try {
            con = DBConnect.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            while (x <= i) {
                pst.setString(x, "%" + userInput + "%");
                x++;
            }
            resultSet = pst.executeQuery();

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
                relic.setIsAvailable(resultSet.getInt("isAvailable"));

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

    ArrayList<MissionLocation> getMissions(String sql, int i, String userInput) throws Exception {
        ArrayList<MissionLocation> missionLocations = new ArrayList<>();
        int x = 1;

        try{
            con = DBConnect.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            while (x <= i) {
                pst.setString(x, userInput + "%");
                x++;
            }
            resultSet = pst.executeQuery();

            while (resultSet.next()){
                MissionLocation missionLocation = new MissionLocation();

                missionLocation.setId(resultSet.getInt("rlaID"));
                missionLocation.setRotation(resultSet.getString("Rotation"));
                missionLocation.setRelicId(resultSet.getInt("RelicID"));
                missionLocation.setLocationId(resultSet.getInt("LocationID"));
                missionLocation.setDropChance(resultSet.getString("DropChance"));
                missionLocation.setPlanet(resultSet.getString("Planet"));
                missionLocation.setLocationName(resultSet.getString("LocationName"));
                missionLocation.setRelicName(resultSet.getString("RelicName"));
                missionLocation.setMission(resultSet.getString("Mission"));

                missionLocations.add(missionLocation);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, st, null);
        }

        return missionLocations;
    }

    ArrayList<Prime> getPrime(String sql) throws Exception {
        ArrayList<Prime> primeList = new ArrayList<>();

        try{
            con = DBConnect.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            resultSet = pst.executeQuery();

            while(resultSet.next()){
                Prime prime = new Prime();
                prime.setId(resultSet.getInt("primeID"));
                prime.setName(resultSet.getString("primeName"));
                primeList.add(prime);
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, st, null);
        }
        return primeList;
    }

    ArrayList<Prime> getFarm(String sql, String userInput) throws Exception {
        ArrayList<Prime> primeList = new ArrayList<>();
        int x = 1;
        int i = 6;

        try{
            con = DBConnect.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            while (x <= i) {
                pst.setString(x, userInput + "%");
                x++;
            }
            resultSet = pst.executeQuery();

            while(resultSet.next()){
                Prime prime = new Prime();
                prime.setId(resultSet.getInt("primeID"));
                prime.setName(resultSet.getString("primeName"));
                primeList.add(prime);
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, st, null);
        }
        return primeList;
    }
}