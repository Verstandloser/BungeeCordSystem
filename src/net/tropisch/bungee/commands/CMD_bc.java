package net.tropisch.bungee.commands;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.tropisch.bungee.main.Main;

public class CMD_bc extends Command {
    public CMD_bc() {
        super("bc", null, new String[]{"broadcast"});
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if (cs.hasPermission("system.broadcast")) {

            if (args.length >= 1) {

                String msg = "";
                for (int i = 0; i < args.length; i++) {
                    msg = msg + args[i] + " ";
                }

                msg = ChatColor.translateAlternateColorCodes('&', msg);
                BungeeCord.getInstance().broadcast("§4§l[§fBroadcast§4§l] §f"+msg);

            }else{
                cs.sendMessage(Main.main.prefix+"§7Du musst eine Nachricht angeben!");
            }

        }else{
            cs.sendMessage(Main.main.noPerm);
        }
    }
}
