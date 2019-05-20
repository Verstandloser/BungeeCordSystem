package net.tropisch.bungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.tropisch.bungee.main.Main;

public class CMD_notify extends Command {
    public CMD_notify() {
        super("notify");
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if (cs instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) cs;
            if (p.hasPermission("system.color.staff")) {

                if (Main.main.teamnotify.contains(p)) {
                    Main.main.teamnotify.remove(p);
                    p.sendMessage(Main.main.prefix+"§7Notify > §cDeaktiviert");
                }else{
                    Main.main.teamnotify.add(p);
                    p.sendMessage(Main.main.prefix+"§7Notify > §aAktiviert");
                }

            }else{
                p.sendMessage(Main.main.noPerm);
            }
        }
    }
}
