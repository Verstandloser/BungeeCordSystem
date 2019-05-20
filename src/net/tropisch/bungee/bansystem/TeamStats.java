package net.tropisch.bungee.bansystem;

import net.md_5.bungee.BungeeCord;
import net.tropisch.bungee.main.Main;
import net.tropisch.bungee.methods.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TeamStats {

    public static TeamStats ts = new TeamStats();

    public void createTeam(UUID uuid){

        MySQL.sql.update("INSERT INTO network_teamstats (uuid, bans, mutes, reports) VALUES ('"+uuid+"','"+"" +
                0+"','"+0+"','"+0+"')");

        if (BungeeCord.getInstance().getPlayer(uuid) != null) {
            BungeeCord.getInstance().getPlayer(uuid).sendMessage(Main.main.prefix+"§7Dein Account für die TeamStats wurde angelegt!");
        }
    }

    public int getBans(UUID uuid){
        ResultSet rs = MySQL.sql.getResult("SELECT * FROM network_teamstats WHERE uuid ='"+uuid+"'");
        try {
            while (rs.next()) {
                return rs.getInt("bans");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public int getMutes(UUID uuid){
        ResultSet rs = MySQL.sql.getResult("SELECT * FROM network_teamstats WHERE uuid ='"+uuid+"'");
        try {
            while (rs.next()) {
                return rs.getInt("mutes");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public int getReports(UUID uuid){
        ResultSet rs = MySQL.sql.getResult("SELECT * FROM network_teamstats WHERE uuid ='"+uuid+"'");
        try {
            while (rs.next()) {
                return rs.getInt("reports");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public void addBan(UUID uuid){
        int i = getBans(uuid);
        i = i+1;
        MySQL.sql.update("UPDATE network_teamstats SET bans = "+i+" WHERE uuid ='"+uuid+"'");
    }

    public void addMute(UUID uuid){
        int i = getMutes(uuid);
        i = i+1;
        MySQL.sql.update("UPDATE network_teamstats SET mutes = "+i+" WHERE uuid ='"+uuid+"'");
    }

    public void addReport(UUID uuid){
        int i = getReports(uuid);
        i = i+1;
        MySQL.sql.update("UPDATE network_teamstats SET reports = "+i+" WHERE uuid ='"+uuid+"'");
    }

    public boolean existsAccount(UUID uuid){

        ResultSet rs = MySQL.sql.getResult("SELECT * FROM network_teamstats WHERE uuid ='"+uuid+"'");
        try {
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {

        }

        return false;
    }

}
