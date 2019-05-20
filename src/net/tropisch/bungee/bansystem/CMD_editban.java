package net.tropisch.bungee.bansystem;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.OfflinePlayer;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.tropisch.bungee.main.Main;
import net.tropisch.bungee.methods.MySQL;

public class CMD_editban extends Command {
    public CMD_editban() {
        super("editban");
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if (cs instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) cs;
            if (p.hasPermission("system.ban")) {

                if (args.length == 2) {

                    ProxiedPlayer t = BungeeCord.getInstance().getPlayer(args[0]);
                    if (t == null) {
                        OfflinePlayer ot = CloudAPI.getInstance().getOfflinePlayer(args[0]);
                        if (ot != null) {
                            if (BanSystem.bs.isBanned(ot.getUniqueId())) {
                                if (BanSystem.bs.getBanner(ot.getUniqueId()).equalsIgnoreCase(p.getName())) {
                                    MySQL.sql.update("UPDATE network_bans SET replay = "+args[1]+" WHERE uuid ='"+ot.getUniqueId()+"'");
                                    p.sendMessage(Main.main.prefix+"§7Du hast das Replay erfolgreich geändert");
                                }else{
                                    p.sendMessage(Main.main.prefix+"§7Da du den Spieler nicht gebannt hast, " +
                                            "kannst du das Replay nicht ändern!");
                                }
                            }else{
                                p.sendMessage(Main.main.prefix+"§7Der Spieler ist nicht gebannt!");
                            }
                        }else{
                            p.sendMessage(Main.main.prefix+"§7Der Spieler existiert nicht");
                        }
                    }else{
                        p.sendMessage(Main.main.prefix+"§7Der Spieler ist nicht gebannt!");
                    }

                }else{
                    p.sendMessage(Main.main.prefix+"§7Bitte gib einen Spieler und eine Replay-ID an!");
                }

            }else{
                p.sendMessage(Main.main.noPerm);
            }
        }
    }
}
