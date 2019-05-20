package net.tropisch.bungee.commands;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.tropisch.bungee.main.Main;

public class CMD_cc extends Command {
    public CMD_cc() {
        super("cc", null, new String[]{"chatclear"});
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if (cs instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) cs;
            if (p.hasPermission("system.chatclear")) {

                for(ProxiedPlayer a : BungeeCord.getInstance().getPlayers()){
                    if (a.getServer().getInfo() == p.getServer().getInfo()) {
                        if (!a.hasPermission("system.chatclear")) {
                            for(int i = 0; i < 200; i++){
                                a.sendMessage(" ");
                            }
                            a.sendMessage(Main.main.prefix+"§7Der Chat wurde geleert!");
                        }
                    }
                }

                for(ProxiedPlayer a : Main.main.teamnotify){
                    a.sendMessage(p.getDisplayName()+" §7hat den Chat auf §6§l"+p.getServer().getInfo().getName()+" §7geleert");
                }

            }else{
                p.sendMessage(Main.main.noPerm);
            }
        }
    }
}
