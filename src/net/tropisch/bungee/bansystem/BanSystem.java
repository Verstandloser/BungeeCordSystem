package net.tropisch.bungee.bansystem;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.player.OfflinePlayer;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.tropisch.bungee.main.Main;
import net.tropisch.bungee.methods.MySQL;
import net.tropisch.bungee.reportsystem.ReportSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class BanSystem {

    public static BanSystem bs = new BanSystem();

    public void onBan(UUID uuid, UUID teamid, String reason, String replay, long end){

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.GERMANY);
        String date = sdf.format(new Date());

        if (end != -1) {
            end = end+System.currentTimeMillis();
        }

        MySQL.sql.update("INSERT INTO network_bans (uuid, team, reason, date, replay, end) VALUES ('"+uuid+"','"+teamid+"','" +
                reason+"','"+date+"','"+replay+"','"+end+"')");

        String cp = CloudAPI.getInstance().getPlayerName(uuid);

        BungeeCord.getInstance().getPlayer(teamid).sendMessage(Main.main.prefix+"§7Du hast §c"+cp+" §7gebannt!");

        if (TeamStats.ts.existsAccount(teamid)) {
            TeamStats.ts.addBan(teamid);
        }else{
            TeamStats.ts.createTeam(teamid);
            TeamStats.ts.addBan(teamid);
        }

        for(ProxiedPlayer a : Main.main.teamnotify){
            if (a.hasPermission("system.ban.notify")) {
                a.sendMessage(Main.main.prefix+"§7Der Spieler §c"+cp+" §7wurde von "+BungeeCord.getInstance().getPlayer(teamid).getDisplayName()+"" +
                        " §7gebannt! Grund: §c"+reason);
            }
        }

        if (BungeeCord.getInstance().getPlayer(uuid) != null) {

            if (ReportSystem.getInstance().reportnotify.containsKey(BungeeCord.getInstance().getPlayer(uuid))) {
                ReportSystem.getInstance().reportnotify.get(BungeeCord.getInstance().getPlayer(uuid)).sendMessage(Main.main.prefix+"§7" +
                        "Ein Spieler, welchen du reportet hast, wurde gebannt! Danke für deine Aufmerksamkeit");
                BungeeCord.getInstance().getPlayer(uuid).disconnect("§cTropisch.net \n\n§7Du wurdest gebannt!\n\n§7Grund: §4"+reason);
            }else{
                BungeeCord.getInstance().getPlayer(uuid).disconnect("§cTropisch.net \n\n§7Du wurdest gebannt!\n\n§7Grund: §4"+reason);
            }
        }

    }

    public boolean isBanned(UUID uuid){

        ResultSet rs = MySQL.sql.getResult("SELECT * FROM network_bans WHERE uuid ='"+uuid+"'");

        try {
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public long getEnd(UUID uuid){

        ResultSet rs = MySQL.sql.getResult("SELECT * FROM network_bans WHERE uuid='"+uuid+"'");

        try {
            if (rs.next()) {
                return rs.getLong("end");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public String getReason(UUID uuid){

        ResultSet rs = MySQL.sql.getResult("SELECT * FROM network_bans WHERE uuid='"+uuid+"'");

        try {
            if (rs.next()) {
                return rs.getString("reason");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Grund konnte nicht geladen werden!";
    }

    public String getReplay(UUID uuid){

        ResultSet rs = MySQL.sql.getResult("SELECT * FROM network_bans WHERE uuid='"+uuid+"'");

        try {
            if (rs.next()) {
                return rs.getString("replay");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Grund konnte nicht geladen werden!";
    }

    public String getDate(UUID uuid){

        ResultSet rs = MySQL.sql.getResult("SELECT * FROM network_bans WHERE uuid='"+uuid+"'");

        try {
            if (rs.next()) {
                return rs.getString("date");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Datum konnte nicht geladen werden!";
    }

    public String getBanner(UUID uuid){

        ResultSet rs = MySQL.sql.getResult("SELECT * FROM network_bans WHERE uuid='"+uuid+"'");

        try {
            if (rs.next()) {
                return(CloudAPI.getInstance().getPlayerName(UUID.fromString(rs.getString("team"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Error -> Try again later <-";
    }

    public String getRemainingTime(UUID uuid){

        long current = System.currentTimeMillis();
        long end = getEnd(uuid);
        if (end == -1) {
            return "Permanent";
        }
        long millis = end - current;
        long seconds = 0;
        long minutes = 0;
        long hours = 0;
        long days = 0;
        long weeks = 0;
        while (millis > 1000) {
            millis -= 1000;
            ++seconds;
        }
        while (seconds > 60) {
            seconds -= 60;
            ++minutes;
        }
        while (minutes > 60) {
            minutes -= 60;
            ++hours;
        }
        while (hours > 24) {
            hours -= 24;
            ++days;
        }
        while (days > 7) {
            days -= 7;
            ++weeks;
        }
        return weeks + " Woche(n) " + days + " Tag(e) " + hours + " Stunde(n) " + minutes + " Minute(n) " + seconds + " Sekunde(n)";

    }

    public String getBanMSG(){
        return "§7Nutze: /ban <Spieler> <ID> (opt. Replay)\n" +
                "Es gibt folgende ID's:\n" +
                "§c1 §7- §fHacking §8(§430 Tage§8)\n" +
                "§c2 §7- §fBugusing §8(§45 Tage§8)\n" +
                "§c3 §7- §fTeaming §8(§43 Tage§8)\n" +
                "§c4 §7- §fTrolling / Spawntrapping §8(§41 Tag§8)\n" +
                "§c5 §7- §fWerbung §8(§430 Tage§8)\n" +
                "§c6 §7- §fName §8(§430 Tage§8)\n" +
                "§c7 §7- §fSkin §8(§415 Tage§8)\n" +
                "§c8 §7- §fBannumgehung / Alt-Account §8(§4Permanent§8)\n" +
                "§c9 §7- §fHausverbot §8(§4Nur Admins§8)";
    }

}
