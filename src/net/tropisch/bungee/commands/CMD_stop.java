package net.tropisch.bungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.tropisch.bungee.main.Main;

public class CMD_stop extends Command {
    public CMD_stop() {
        super("stop");
    }

    @Override
    public void execute(CommandSender cs, String[] strings) {
        if (cs.hasPermission("system.stop")) {

            cs.sendMessage("§c===========================================");
            cs.sendMessage("§7/bstop (Proxy) §8- §fStoppt deinen/einen Proxy");
            cs.sendMessage("§7/sstop (Proxy) §8- §fStoppt deinen/einen Server");
            cs.sendMessage("§7/nstop (Proxy) §8- §fStoppt das Netzwerk");
            cs.sendMessage("§c===========================================");

        }else{
            cs.sendMessage(Main.main.noPerm);
        }
    }
}
