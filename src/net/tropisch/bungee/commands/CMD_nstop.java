package net.tropisch.bungee.commands;

import de.dytanic.cloudnet.api.CloudAPI;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.tropisch.bungee.main.Main;

import java.util.ArrayList;

public class CMD_nstop extends Command {
    public CMD_nstop() {
        super("nstop");
    }

    public ArrayList<ProxiedPlayer> shure = new ArrayList<>();

    @Override
    public void execute(CommandSender cs, String[] args) {
        if (cs instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) cs;
            if (p.hasPermission("system.nstop")) {

                if (shure.contains(p)) {

                    CloudAPI.getInstance().shutdown();

                }else{
                    p.sendMessage(Main.main.prefix+"§7Bist du dir sicher? Dann schreib die Nachricht nochmal!");
                    shure.add(p);
                }

            }else{
                p.sendMessage(Main.main.noPerm);
            }
        }
    }
}
