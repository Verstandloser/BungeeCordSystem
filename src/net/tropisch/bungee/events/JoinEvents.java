package net.tropisch.bungee.events;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.tropisch.bungee.bansystem.BanSystem;
import net.tropisch.bungee.main.Main;
import net.tropisch.bungee.methods.MySQL;

public class JoinEvents implements Listener {

    @EventHandler
    public void onConnect(PostLoginEvent e){
        ProxiedPlayer p = e.getPlayer();
        if (p.hasPermission("system.color.admin")) {
            p.setDisplayName("§4"+p.getName());
            Main.main.teamnotify.add(p);
            sendTeam(p);
        }else if (p.hasPermission("system.color.staff")) {
            p.setDisplayName("§9"+p.getName());
            Main.main.teamnotify.add(p);
            sendTeam(p);
        }else if (p.hasPermission("system.color.partner")) {
            p.setDisplayName("§c"+p.getName());
        }else if (p.hasPermission("system.color.yt")) {
            p.setDisplayName("§5"+p.getName());
        }else if (p.hasPermission("system.color.tropic")) {
            p.setDisplayName("§a"+p.getName());
        }else if (p.hasPermission("system.color.vip+")) {
            p.setDisplayName("§6"+p.getName());
        }else if (p.hasPermission("system.color.vip")) {
            p.setDisplayName("§6"+p.getName());
        }else {
            p.setDisplayName("§7"+p.getName());
        }
    }

    @EventHandler
    public void onBan(ServerConnectEvent e){
        ProxiedPlayer p = e.getPlayer();
        if (BanSystem.bs.isBanned(e.getPlayer().getUniqueId())) {

            if (BanSystem.bs.getRemainingTime(p.getUniqueId()).equalsIgnoreCase("0 Woche(n) 0 Tag(e) 0 Stunde(n) 0 Minute(n) 0 Sekunde(n)")) {
                MySQL.sql.update("DELETE FROM network_bans WHERE uuid='"+p.getUniqueId()+"'");

                for(ProxiedPlayer a : Main.main.teamnotify){
                    if (a.hasPermission("system.ban.notify")) {
                        a.sendMessage(Main.main.prefix+"§7Der Spieler §c"+p.getName()+" §7wurde von dem §4System §7" +
                                "§7entbannt!");
                    }
                }
            }else{
                e.setCancelled(true);
                e.getPlayer().disconnect("§cTropisch.net \n\n§7Du wurdest gebannt!\n\n" +
                        "§7Grund: §c"+BanSystem.bs.getReason(p.getUniqueId())+"\n" +
                        "§7Ban-Datum: §c"+BanSystem.bs.getDate(p.getUniqueId())+"\n" +
                        "§7Verbleibende Zeit: §c"+BanSystem.bs.getRemainingTime(p.getUniqueId())+"\n\n" +
                        "§7Du kannst auf §cTropisch.net/forum §7einen Entbannungsantrag stellen!");

            }

        }else{
            e.setCancelled(false);
        }
    }

    private void sendTeam(ProxiedPlayer t){
        for(ProxiedPlayer a : Main.main.teamnotify){
            a.sendMessage("§9[§fTEAM§9] "+t.getDisplayName()+" §7ist nun §aonline§7!");
        }
    }

}
