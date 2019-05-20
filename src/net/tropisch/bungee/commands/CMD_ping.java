package net.tropisch.bungee.commands;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.tropisch.bungee.main.Main;

public class CMD_ping extends Command {
    public CMD_ping() {
        super("ping");
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if (cs instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) cs;
            if (args.length == 0) {
                p.sendMessage(Main.main.prefix+"§7Dein Ping beträgt §c"+p.getPing()+"§7ms");
            }else if (args.length == 1) {
                if (p.hasPermission("system.ping")) {
                    ProxiedPlayer t = BungeeCord.getInstance().getPlayer(args[0]);
                    if (t != null) {
                        p.sendMessage(Main.main.prefix+"§7Der Ping von "+t.getDisplayName()+" §7beträgt §c"+t.getPing()+"§7ms");
                    }else{
                        p.sendMessage(Main.main.prefix+"§7Der Spieler ist §cnicht §7online!");
                    }
                }else{
                    p.sendMessage(Main.main.prefix+"§7Dein Ping beträgt §c"+p.getPing()+"§7ms");
                }
            }
        }
    }
}
