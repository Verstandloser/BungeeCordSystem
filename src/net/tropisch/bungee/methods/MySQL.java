package net.tropisch.bungee.methods;

import net.md_5.bungee.BungeeCord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {

    public static MySQL sql = new MySQL();
    
    public String username = "root";
    public String pass = "";
    public String database = "system";
    public String host = "localhost";

    public Connection con;

    public void connect(){

        try {
            con = DriverManager.getConnection("jdbc:mysql://"+host+":3306"+"/"+database, username, pass);
            BungeeCord.getInstance().getConsole().sendMessage("§f[§eMySQL§f] §aConnected");
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
            BungeeCord.getInstance().getConsole().sendMessage("§f[§eMySQL§f] §cError while connecting");
        }
    }

    public void close(){
        if(!isConnected()){
            try {
                con.close();
                BungeeCord.getInstance().getConsole().sendMessage("§f[§eMySQL§f] §cDisconnected");
            } catch (SQLException e) {
                e.printStackTrace();
                BungeeCord.getInstance().getConsole().sendMessage("§f[§eMySQL§f] §cError while disconnecting");

            }

        }
    }

    public boolean isConnected(){
        return con != null;
    }

    public void createTable(){


        if(isConnected()){
            try {

                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS network_bans (uuid VARCHAR(100), " +
                        "team VARCHAR(100), reason VARCHAR(100), date VARCHAR(100), replay VARCHAR(100), end VARCHAR(100))");

                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS network_mutes (uuid VARCHAR(100), " +
                        "team VARCHAR(100), reason VARCHAR(100), date VARCHAR(100), beweis VARCHAR(100), end VARCHAR(100))");

                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS network_teamstats (uuid VARCHAR(100), " +
                        "bans INTEGER(100), mutes INTEGER(100), reports INTEGER(100))");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void update(String qry){
        if(isConnected()){
            try {
                con.createStatement().executeUpdate(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet getResult(String qry){
        if(isConnected()){
            try {
                return con.createStatement().executeQuery(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
