package net.tropisch.bungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.tropisch.bungee.main.Main;

public class CMD_tc extends Command {
    public CMD_tc() {
        super("tc", null, new String[]{"teamchat"});
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if (cs instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) cs;
            if (p.hasPermission("system.teamchat")) {

                if (args.length >= 1) {

                    if (Main.main.teamnotify.contains(p)) {

                        String msg = "";
                        for (int i = 0; i < args.length; i++) {
                            msg = msg + args[i] + " ";
                        }

                        for(ProxiedPlayer a : Main.main.teamnotify){
                            a.sendMessage("§9[§fTEAM§9] "+p.getDisplayName()+" §8» §f"+msg);
                        }

                    }else{
                        p.sendMessage(Main.main.prefix+"§7Bitte melde dich erst an, bevor du eine Nachricht versendest! " +
                                "§8(§c/notify§8)");
                    }

                }else{
                    p.sendMessage(Main.main.prefix+"§7Du musst eine Nachricht angeben!");
                }

            }else{
                p.sendMessage(Main.main.noPerm);
            }
        }
    }
}
