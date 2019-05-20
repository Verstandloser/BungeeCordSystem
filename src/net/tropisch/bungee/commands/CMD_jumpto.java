package net.tropisch.bungee.commands;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.tropisch.bungee.main.Main;

public class CMD_jumpto extends Command {
    public CMD_jumpto() {
        super("jumpto", null, new String[]{"goto"});
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if (cs instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) cs;
            if (p.hasPermission("system.jumpto")) {

                if (args.length == 1) {

                    ProxiedPlayer t = BungeeCord.getInstance().getPlayer(args[0]);
                    if (t != null) {

                        if (t != p) {

                            if (t.getServer().getInfo() != p.getServer().getInfo()) {
                                p.sendMessage(Main.main.prefix+"§7Du bist "+t.getDisplayName()+" §7nachgesprungen!");
                                p.connect(t.getServer().getInfo());
                            }else{
                                p.sendMessage(Main.main.prefix+"§7Du bist bereits auf diesem Server");
                            }

                        }else{
                            p.sendMessage(Main.main.prefix+"§7Du kannst §cnicht §7zu dir selbst springen");
                        }

                    }else{
                        p.sendMessage(Main.main.prefix+"§7Der Spieler ist §cnicht §7online!");
                    }

                }else{
                    p.sendMessage(Main.main.prefix+"§7Bitte gib einen Spieler an!");
                }

            }else{
                p.sendMessage(Main.main.noPerm);
            }
        }
    }
}
