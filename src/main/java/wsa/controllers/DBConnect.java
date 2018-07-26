package wsa.controllers;

import java.sql.*;
import java.util.*;
import java.io.*;

class DBConnect {

    private static Properties dbProperties;
    private static String url;
    private static Driver dbDriver;
    private static InputStream input;

    static {
        try {
            dbProperties = new Properties();
            dbProperties.load(new FileInputStream("config.properties"));
            dbDriver = (Driver)Class.forName(dbProperties.getProperty("DriverClassName")).newInstance();
            url = dbProperties.getProperty("url");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(input != null){
                try{
                    input.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    static Connection getConnection() throws Exception {
        return dbDriver.connect(url, dbProperties);
    }

    static void closeConnection(Connection con, Statement st, ResultSet rs) throws Exception {
        if (con != null)
            con.close();
        if(st != null)
            st.close();
        if(rs != null)
            rs.close();
    }

    /* Test config.properties is connecting
    public static void main(String[] args){
        Properties prop = new Properties();
        InputStream input = null;

        try{
            input = new FileInputStream("config.properties");
            prop.load(input);
            System.out.println(prop.getProperty("url"));
            System.out.println(prop.getProperty("DriverClassName"));
            System.out.println(prop.getProperty("user"));
            System.out.println(prop.getProperty("password"));
        }catch(IOException io){
            io.printStackTrace();
        }finally{
            if(input != null){
                try{
                    input.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }*/
}
