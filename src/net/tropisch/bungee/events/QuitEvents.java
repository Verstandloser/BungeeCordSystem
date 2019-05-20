package net.tropisch.bungee.events;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.tropisch.bungee.main.Main;
import net.tropisch.bungee.reportsystem.ReportSystem;

public class QuitEvents implements Listener {

    @EventHandler
    public void onQuit(PlayerDisconnectEvent e){
        ProxiedPlayer p = e.getPlayer();
        if (p.hasPermission("system.color.admin")) {
            sendTeam(p);
            if (Main.main.teamnotify.contains(p)) {
                Main.main.teamnotify.remove(p);
            }
        }else if (p.hasPermission("system.color.staff")) {
            sendTeam(p);
            if (Main.main.teamnotify.contains(p)) {
                Main.main.teamnotify.remove(p);
            }
        }
    }

    @EventHandler
    public void onReportRemove(PlayerDisconnectEvent e){
        ProxiedPlayer p = e.getPlayer();
        if (ReportSystem.getInstance().reportlist.contains(p)) {
            ReportSystem.getInstance().reportlist.remove(p);
            ReportSystem.getInstance().reportinfo.remove(p);
            ReportSystem.getInstance().reportnotify.remove(p);
        }
    }

    private void sendTeam(ProxiedPlayer t){
        for(ProxiedPlayer a : Main.main.teamnotify){
            a.sendMessage("§9[§fTEAM§9] "+t.getDisplayName()+" §7ist nun §coffline§7!");
        }
    }

}
