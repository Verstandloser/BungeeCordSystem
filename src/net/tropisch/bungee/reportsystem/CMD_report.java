package net.tropisch.bungee.reportsystem;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.tropisch.bungee.main.Main;

public class CMD_report extends Command {
    public CMD_report() {
        super("report");
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if (cs instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) cs;
            if (args.length == 2) {

                ProxiedPlayer t = BungeeCord.getInstance().getPlayer(args[0]);
                if (t != null) {

                    if (t != p) {

                        if (!t.hasPermission("system.report.bypass")) {

                            if (!ReportSystem.getInstance().reportlist.contains(t)) {
                                if (args[1].equalsIgnoreCase("Hacking")) {
                                    ReportSystem.getInstance().createReport(t, p, "Hacking");
                                }else if (args[1].equalsIgnoreCase("Teaming")) {
                                    ReportSystem.getInstance().createReport(t, p, "Teaming");
                                }else if (args[1].equalsIgnoreCase("Bugusing")) {
                                    ReportSystem.getInstance().createReport(t, p, "Bugusing");
                                }else if (args[1].equalsIgnoreCase("Werbung")) {
                                    ReportSystem.getInstance().createReport(t, p, "Werbung");
                                }else if (args[1].equalsIgnoreCase("Chatverhalten")) {
                                    ReportSystem.getInstance().createReport(t, p, "Chatverhalten");
                                }else if (args[1].equalsIgnoreCase("Skin")) {
                                    ReportSystem.getInstance().createReport(t, p, "Skin");
                                }else if (args[1].equalsIgnoreCase("Name")) {
                                    ReportSystem.getInstance().createReport(t, p, "Name");
                                }else if (args[1].equalsIgnoreCase("Trolling")) {
                                    ReportSystem.getInstance().createReport(t, p, "Trolling");
                                }else if (args[1].equalsIgnoreCase("Sonstieges")) {
                                    ReportSystem.getInstance().createReport(t, p, "Sonstieges");
                                }
                            }else{
                                int i = 0;
                                for(ProxiedPlayer a : Main.main.teamnotify){
                                    if (a.hasPermission("system.report")) {
                                        i = i+1;
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

                        }else{
                            int i = 0;
                            for(ProxiedPlayer a : Main.main.teamnotify){
                                if (a.hasPermission("system.report")) {
                                    i = i+1;
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

                    }else{
                        p.sendMessage(Main.main.prefix+"§7Du kannst dich nicht selbst melden!");
                    }

                }else{
                    p.sendMessage(Main.main.prefix+"§7Der Spieler ist §cnicht §7online!");
                }

            } else if (args.length == 1) {

                if (p.hasPermission("system.report")) {
                    if (args[0].equalsIgnoreCase("auto")) {
                        if (ReportSystem.getInstance().reportlist.size() >= 1) {
                            ReportSystem.getInstance().openRandomReport(p);
                        }else{
                            p.sendMessage(Main.main.prefix+"§7Derzeit ist kein Report offen");
                        }
                    }else if (args[0].equalsIgnoreCase("list")) {
                        if (ReportSystem.getInstance().reportlist.size() >= 2) {
                            p.sendMessage(Main.main.prefix+"§7Es sind derzeit §c"+ReportSystem.getInstance().reportlist.size()+" §7Reports offen!");
                        }else if (ReportSystem.getInstance().reportlist.size() == 1) {
                            p.sendMessage(Main.main.prefix+"§7Es ist derzeit §c"+ReportSystem.getInstance().reportlist.size()+" §7Report offen!");
                        }else{
                            p.sendMessage(Main.main.prefix+"§7Derzeit ist kein Report offen");
                        }
                    }
                }else{
                    p.sendMessage(Main.main.prefix+"§7Bitte gib einen Spieler und einen Grund an! Gründe sind:\n" +
                            "§7Hacking, Teaming, Bugusing, Werbung, Chatverhalten, Skin, Name, Trolling, Sonstieges");
                }

            }else{
                p.sendMessage(Main.main.prefix+"§7Bitte gib einen Spieler und einen Grund an! Gründe sind:\n" +
                        "§7Hacking, Teaming, Bugusing, Werbung, Chatverhalten, Skin, Name, Trolling, Sonstieges");
            }
        }
    }
}
