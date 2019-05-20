package net.tropisch.bungee.bansystem;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.tropisch.bungee.main.Main;
import net.tropisch.bungee.methods.UUIDFetcher;

public class CMD_kick extends Command {
    public CMD_kick() {
        super("kick");
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if (cs instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) cs;
            if (p.hasPermission("system.kick")) {

                if (args.length >= 2) {

                    ProxiedPlayer t = BungeeCord.getInstance().getPlayer(args[0]);

                    String msg = "";
                    for(int i = 1; i< args.length; i++){
                        msg = msg+args[i];
                    }

                    if (t != null) {

                        if (t != p) {

                            if (!t.hasPermission("system.kick.bypass")) {

                                p.sendMessage(Main.main.prefix+"§7Du hast "+t.getDisplayName()+" §7gekickt!");
                                for(ProxiedPlayer a : Main.main.teamnotify){
                                    if (a.hasPermission("system.kick.notify")) {
                                        a.sendMessage(Main.main.prefix+""+t.getDisplayName()+" §7wurde von "+p.getDisplayName()+" §7gekickt!\n" +
                                                "§7Grund: §c"+msg);
                                    }
                                }
                                t.disconnect("§cTropisch.net \n\n §7Du wurdest gekickt!\n\n\n§7Grund: §c"+msg);

                            }else{

                                if (p.getName().equalsIgnoreCase("STRG5")) {

                                    p.sendMessage(Main.main.prefix+"§7Du hast "+t.getDisplayName()+" §7gekickt!");
                                    for(ProxiedPlayer a : Main.main.teamnotify){
                                        if (a.hasPermission("system.kick.notify")) {
                                            a.sendMessage(Main.main.prefix+""+t.getDisplayName()+" §7wurde von "+p.getDisplayName()+" §7gekickt!\n" +
                                                    "§7Grund: §c"+msg);
                                        }
                                    }
                                    t.disconnect("§cTropisch.net \n\n §7Du wurdest gekickt!\n\n\n§7Grund: §c"+msg);

                                }else{
                                    p.sendMessage(Main.main.prefix+"§7Du darfst diesen Spieler nicht kicken");
                                }

                            }

                        }else{
                            p.sendMessage(Main.main.prefix+"§7Du kannst dich nicht selbst kicken!");
                        }

                    }else{
                        p.sendMessage(Main.main.prefix+"§7Der Spieler ist nicht online!");
                    }

                }else{
                    p.sendMessage(Main.main.prefix+"§7Bitte gib einen Spieler und einen Grund an");
                }

            }else{
                p.sendMessage(Main.main.noPerm);
            }
        }
    }
}
