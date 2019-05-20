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

import java.util.ArrayList;

public class CMD_joinme extends Command {
    public CMD_joinme() {
        super("joinme");
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if (cs instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) cs;
            if (args.length == 0) {

                for(ProxiedPlayer a : BungeeCord.getInstance().getPlayers()){
                    net.md_5.bungee.api.chat.TextComponent tc = new TextComponent("" +
                            "§c=============================================\n\n\n" +
                            ""+p.getDisplayName()+" §7spielt gerade auf §b"+p.getServer().getInfo().getName()+"\n" +
                            "                            Klicker hier, um zu joinen!\n\n\n" +
                            "§c=============================================");
                    tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Betrete seinen Server").create()));
                    tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/joinme ioawijoaioja " + p.getName()));
                    a.sendMessage(tc);
                }

            } else if (args.length == 2) {

                if (args[0].equalsIgnoreCase("ioawijoaioja")) {

                    ProxiedPlayer t = BungeeCord.getInstance().getPlayer(args[1]);
                    if (t != null) {

                        p.connect(t.getServer().getInfo());

                    }else{
                        p.sendMessage(Main.main.prefix+"§7Der Spieler ist nicht online!");
                    }

                }

            }
        }
    }
}
