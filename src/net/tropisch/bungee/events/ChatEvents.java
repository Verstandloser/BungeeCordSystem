package net.tropisch.bungee.events;

import de.dytanic.cloudnet.lib.utility.threading.RunnabledTask;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.tropisch.bungee.bansystem.MuteSystem;
import net.tropisch.bungee.main.Main;
import net.tropisch.bungee.methods.MySQL;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChatEvents implements Listener {

    @EventHandler
    public void onChat(ChatEvent e){
        if (e.getSender() instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) e.getSender();

            if (!MuteSystem.bs.isMuted(p.getUniqueId())) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.GERMANY);
                String date = sdf.format(new Date());
                if (!Main.main.chatlog.containsKey(p.getName())) {
                    Main.main.chatlog.put(p.getName(), date+" §b> §f"+e.getMessage());
                }else{
                    Main.main.chatlog.put(p.getName(), Main.main.chatlog.get(p.getName())+"\n"+date+" §b> §f"+e.getMessage());
                }

                if (Main.main.blockedWords.contains(e.getMessage().toUpperCase())) {

                    net.md_5.bungee.api.chat.TextComponent tc = new TextComponent("§c[§fFilter§c] "+p.getDisplayName()+" §8» §7"+e.getMessage());
                    tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Mute ihn").create()));
                    tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/mute "));

                    for(ProxiedPlayer a : Main.main.teamnotify){
                        if (a.hasPermission("system.filter")) {
                            a.sendMessage(tc);
                        }
                    }

                }
            }else{
                if (!e.getMessage().startsWith("/")) {
                    if (MuteSystem.bs.getEnd(p.getUniqueId()) > System.currentTimeMillis()) {
                        e.setCancelled(true);
                        BungeeCord.getInstance().getScheduler().runAsync(Main.main, new Runnable() {
                            @Override
                            public void run() {
                                p.sendMessage("\n\n§7Du wurdest gemutet!\n" +
                                        "§7Grund: §c"+MuteSystem.bs.getReason(p.getUniqueId())+"\n" +
                                        "§7Verbleibende Zeit: §c"+MuteSystem.bs.getRemainingTime(p.getUniqueId())+"\n\n");
                            }
                        });
                    }else{
                        MySQL.sql.update("DELETE FROM network_mutes WHERE uuid='"+p.getUniqueId()+"'");
                        p.sendMessage(Main.main.prefix+"§7Dein Mute ist abgelaufen!");

                        for(ProxiedPlayer a : Main.main.teamnotify){
                            if (a.hasPermission("system.mute.notify")) {
                                a.sendMessage(Main.main.prefix+"§7Der Spieler §c"+p.getName()+" §7wurde von dem §4System §7" +
                                        "§7entmutet!");
                            }
                        }
                    }
                }
            }
        }
    }

}
