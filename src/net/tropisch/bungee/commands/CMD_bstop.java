package net.tropisch.bungee.commands;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.tropisch.bungee.main.Main;

public class CMD_bstop extends Command {
    public CMD_bstop() {
        super("bstop");
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if (cs instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) cs;
            if (p.hasPermission("system.bstop")) {

                if (args.length == 0) {
                    CloudPlayer cp = CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId());
                    for(ProxiedPlayer a : Main.main.teamnotify){
                        if (a.hasPermission("system.bstop")) {
                            a.sendMessage("§4[§7SYSTEM§4] §b"+cp.getProxy()+" §7wurde von "+p.getDisplayName()+" §7gestoppt!");
                        }
                    }
                    for(ProxiedPlayer a : BungeeCord.getInstance().getPlayers()){
                        a.disconnect("§cTropisch.net \n\n" +
                                "§7Dein BungeeCord wurde gestoppt!");
                    }
                    BungeeCord.getInstance().stop();
                } else if (args.length == 1) {
                    for(ProxiedPlayer a : Main.main.teamnotify){
                        if (a.hasPermission("system.bstop")) {
                            a.sendMessage("§4[§7SYSTEM§4] §b"+args[0]+" §7wurde von "+p.getDisplayName()+" §7gestoppt!");
                        }
                    }
                    CloudAPI.getInstance().stopProxy(args[0]);
                }else{
                    p.sendMessage(Main.main.prefix+"§7Du kannst optional einen Proxy angeben!");
                }

            }else{
                p.sendMessage(Main.main.noPerm);
            }
        }
    }
}
