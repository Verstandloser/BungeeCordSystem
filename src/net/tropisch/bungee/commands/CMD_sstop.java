package net.tropisch.bungee.commands;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.tropisch.bungee.main.Main;

public class CMD_sstop extends Command {
    public CMD_sstop() {
        super("sstop");
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if (cs instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) cs;
            if (p.hasPermission("system.sstop")) {

                if (args.length == 0) {
                    for(ProxiedPlayer a : Main.main.teamnotify){
                        if (a.hasPermission("system.sstop")) {
                            a.sendMessage("§4[§7SYSTEM§4] §b"+p.getServer().getInfo().getName()+" §7wurde von "+p.getDisplayName()+" §7gestoppt!");
                        }
                    }
                    CloudAPI.getInstance().stopServer(p.getServer().getInfo().getName());
                } else if (args.length == 1) {
                    for(ProxiedPlayer a : Main.main.teamnotify){
                        if (a.hasPermission("system.sstop")) {
                            a.sendMessage("§4[§7SYSTEM§4] §b"+args[0]+" §7wurde von "+p.getDisplayName()+" §7gestoppt!");
                        }
                    }
                    CloudAPI.getInstance().stopServer(args[0]);
                }else{
                    p.sendMessage(Main.main.prefix+"§7Du kannst optional einen Proxy angeben!");
                }

            }else{
                p.sendMessage(Main.main.noPerm);
            }
        }
    }
}
