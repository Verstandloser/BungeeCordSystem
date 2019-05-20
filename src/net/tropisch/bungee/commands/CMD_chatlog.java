package net.tropisch.bungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.tropisch.bungee.main.Main;

public class CMD_chatlog extends Command {
    public CMD_chatlog() {
        super("chatlog", null, new String[]{"chatlog"});
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if (cs instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) cs;
            if (p.hasPermission("system.chatlog")) {

                if (args.length == 1) {

                    if (Main.main.chatlog.containsKey(args[0])) {

                        p.sendMessage("§c============================");
                        p.sendMessage("§7Chatlog von: §f"+args[0]);
                        p.sendMessage("§3============================");
                        p.sendMessage("§7"+Main.main.chatlog.get(args[0]));
                        p.sendMessage("§3============================");
                        p.sendMessage("§c============================");

                    }else{
                        p.sendMessage(Main.main.prefix+"§7Es konnte kein Chatlog gefunden werden!");
                    }

                }else{
                    p.sendMessage(Main.main.prefix+"§7Du musst einen Spieler angeben!");
                }

            }else{
                p.sendMessage(Main.main.noPerm);
            }
        }
    }
}
