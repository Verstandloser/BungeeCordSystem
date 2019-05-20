package net.tropisch.bungee.commands;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.tropisch.bungee.main.Main;

public class CMD_find extends Command {
    public CMD_find() {
        super("find", null, new String[]{"findme, whereami, getserver"});
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if (cs instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) cs;
            if (args.length == 0) {
                p.sendMessage(Main.main.prefix+"§7Du befindest dich auf §c"+p.getServer().getInfo().getName());
            } else if (args.length == 1) {
                if (p.hasPermission("system.find")) {
                    ProxiedPlayer t = BungeeCord.getInstance().getPlayer(args[0]);
                    if (t != null) {
                        if (t != p) {
                            net.md_5.bungee.api.chat.TextComponent tc = new TextComponent(Main.main.prefix+"§7"+t.getDisplayName()+" §7befindet sich auf §c"+t.getServer().getInfo().getName());
                            tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Springe zu ihm").create()));
                            tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/jumpto " + t.getName()));
                            p.sendMessage(tc);
                        }else{
                            p.sendMessage(Main.main.prefix+"§7Du befindest dich auf §c"+p.getServer().getInfo().getName());
                        }
                    }else{
                        p.sendMessage(Main.main.prefix+"§7Der Spieler ist §cnicht §7online");
                    }
                }else{
                    p.sendMessage(Main.main.prefix+"§7Du befindest dich auf §c"+p.getServer().getInfo().getName());
                }
            }
        }
    }
}
