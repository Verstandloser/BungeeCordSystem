package net.tropisch.bungee.bansystem;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.OfflinePlayer;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.tropisch.bungee.main.Main;
import net.tropisch.bungee.methods.MySQL;

public class CMD_editmute extends Command {
    public CMD_editmute() {
        super("editmute");
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if (cs instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) cs;
            if (p.hasPermission("system.mute")) {

                if (args.length == 2) {

                    ProxiedPlayer t = BungeeCord.getInstance().getPlayer(args[0]);
                    if (t != null) {
                        if (MuteSystem.bs.isMuted(t.getUniqueId())) {
                            if (MuteSystem.bs.getBanner(t.getUniqueId()).equalsIgnoreCase(p.getName())) {
                                MySQL.sql.update("UPDATE network_mutes SET beweis = "+args[1]+" WHERE uuid ='"+t.getUniqueId()+"'");
                                p.sendMessage(Main.main.prefix+"§7Du hast den Beweis erfolgreich geändert");
                            }else{
                                p.sendMessage(Main.main.prefix+"§7Da du den Spieler nicht gemutet hast, " +
                                        "kannst du den Beweis nicht ändern!");
                            }
                        }else{
                            p.sendMessage(Main.main.prefix+"§7Der Spieler ist nicht gemutet!");
                        }
                    }else{
                        OfflinePlayer ot = CloudAPI.getInstance().getOfflinePlayer(args[0]);
                        if (ot != null) {
                            if (MuteSystem.bs.isMuted(ot.getUniqueId())) {
                                if (MuteSystem.bs.getBanner(ot.getUniqueId()).equalsIgnoreCase(p.getName())) {
                                    MySQL.sql.update("UPDATE network_mutes SET beweis = "+args[1]+" WHERE uuid ='"+ot.getUniqueId()+"'");
                                    p.sendMessage(Main.main.prefix+"§7Du hast den Beweis erfolgreich geändert");
                                }else{
                                    p.sendMessage(Main.main.prefix+"§7Da du den Spieler nicht gemutet hast, " +
                                            "kannst du den Beweis nicht ändern!");
                                }
                            }else{
                                p.sendMessage(Main.main.prefix+"§7Der Spieler ist nicht gemutet!");
                            }
                        }else{
                            p.sendMessage(Main.main.prefix+"§7Der Spieler existiert nicht!");
                        }
                    }

                }else{
                    p.sendMessage(Main.main.prefix+"§7Bitte gib einen Spieler und einen Beweis an!");
                }

            }else{
                p.sendMessage(Main.main.noPerm);
            }
        }
    }
}
