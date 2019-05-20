package net.tropisch.bungee.reportsystem;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.tropisch.bungee.bansystem.TeamStats;
import net.tropisch.bungee.main.Main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ReportSystem {

    public static ReportSystem rs = new ReportSystem();

    public static ReportSystem getInstance(){
        return rs;
    }

    public ArrayList<ProxiedPlayer> reportlist = new ArrayList<>();
    public HashMap<ProxiedPlayer, String> reportinfo = new HashMap<>();
    public HashMap<ProxiedPlayer, ProxiedPlayer> reportnotify = new HashMap<>();

    public void createReport(ProxiedPlayer t, ProxiedPlayer p, String reason){
        reportlist.add(t);
        reportinfo.put(t, reason);
        reportnotify.put(t, p);

        int i = 0;
        for(ProxiedPlayer a : Main.main.teamnotify){
            if (a.hasPermission("system.report")) {
                i = i+1;
                a.sendMessage(Main.main.prefix+"§7Es wurde ein neuer Report eingereicht! Gesamt offene Reports (§c"+reportlist.size()+"" +
                        "§7)");
            }
        }
        if (i == 1) {
            p.sendMessage(Main.main.prefix+"§7Vielen Dank für deinen Report! Derzeit kann sich §c"+i+" " +
                    "§7Teammitglied um deinen Report kümmern");
        }else{
            p.sendMessage(Main.main.prefix+"§7Vielen Dank für deinen Report! Derzeit können sich §c"+i+" " +
                    "§7Teammitglieder um deinen Report kümmern");
        }
    }

    public void openRandomReport(ProxiedPlayer team){
        Random random = new Random();
        int i = random.nextInt(reportlist.size());
        ProxiedPlayer t = (ProxiedPlayer) ReportSystem.rs.reportlist.toArray()[i];

        team.sendMessage("§7Du bearbeitest nun den Report von "+t.getDisplayName()+"\n" +
                "§7Grund: §c"+reportinfo.get(t)+"\n" +
                "§7Von: "+reportnotify.get(t).getName());
        team.connect(t.getServer().getInfo());

        reportinfo.remove(t);
        reportlist.remove(t);

        if (reportnotify.get(t) != null) {
            reportnotify.get(t).sendMessage(Main.main.prefix+"§7Dein Report wird nun bearbeitet!");
        }

        if (TeamStats.ts.existsAccount(team.getUniqueId())) {
            TeamStats.ts.addReport(team.getUniqueId());
        }else{
            TeamStats.ts.createTeam(team.getUniqueId());
            TeamStats.ts.addReport(team.getUniqueId());
        }
    }
}
