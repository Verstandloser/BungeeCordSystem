package net.tropisch.bungee.bansystem;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.OfflinePlayer;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.tropisch.bungee.main.Main;
import net.tropisch.bungee.methods.MySQL;

public class CMD_unban extends Command {
    public CMD_unban() {
        super("unban");
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if (cs instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) cs;
            BungeeCord.getInstance().getScheduler().runAsync(Main.main, new Runnable() {
                @Override
                public void run() {
                    if (p.hasPermission("system.unban")) {

                        if (args.length == 1) {

                            ProxiedPlayer t = BungeeCord.getInstance().getPlayer(args[0]);
                            if(t == null){

                                OfflinePlayer ot = CloudAPI.getInstance().getOfflinePlayer(args[0]);
                                if (ot != null) {

                                    if (BanSystem.bs.isBanned(ot.getUniqueId())) {
                                        MySQL.sql.update("DELETE FROM network_bans WHERE uuid='"+ot.getUniqueId()+"'");
                                        p.sendMessage(Main.main.prefix+"§7Du hast §c"+ot.getName()+" §7entbannt!");

                                        for(ProxiedPlayer a : Main.main.teamnotify){
                                            if (a.hasPermission("system.ban.notify")) {
                                                a.sendMessage(Main.main.prefix+"§7Der Spieler §c"+ot.getName()+" §7wurde von "+p.getDisplayName()+" " +
                                                        "§7entbannt!");
                                            }
                                        }
                                    }else{
                                        p.sendMessage(Main.main.prefix+"§7Der Spieler ist nicht gebannt!");
                                    }

                                }else{
                                    p.sendMessage(Main.main.prefix+"§7Der Spieler existiert nicht!");
                                }

                            }else{
                                p.sendMessage(Main.main.prefix+"§7Der Spieler ist nicht gebannt!");
                            }

                        }else{
                            p.sendMessage(Main.main.prefix+"§7Bitte gib einen Spieler an");
                        }

                    }else{
                        p.sendMessage(Main.main.noPerm);
                    }
                }
            });
        }
    }
}
