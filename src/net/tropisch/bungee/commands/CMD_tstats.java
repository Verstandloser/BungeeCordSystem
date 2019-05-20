package net.tropisch.bungee.commands;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.OfflinePlayer;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.score.Team;
import net.tropisch.bungee.bansystem.TeamStats;
import net.tropisch.bungee.main.Main;

import java.util.UUID;

public class CMD_tstats extends Command {
    public CMD_tstats() {
        super("tstats", null, new String[]{"teamstats"});
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if (cs instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) cs;
            BungeeCord.getInstance().getScheduler().runAsync(Main.main, new Runnable() {
                @Override
                public void run() {
                    if (p.hasPermission("system.tstats")) {

                        if (args.length == 0) {
                            if (TeamStats.ts.existsAccount(p.getUniqueId())) {

                                p.sendMessage("§7Deine Statistiken:\n" +
                                        "§7Banns: §c"+TeamStats.ts.getBans(p.getUniqueId())+"\n" +
                                        "§7Mutes: §c"+TeamStats.ts.getMutes(p.getUniqueId())+"\n" +
                                        "§7Reports: §c"+TeamStats.ts.getReports(p.getUniqueId()));

                            }else{
                                TeamStats.ts.createTeam(p.getUniqueId());
                            }
                        } else if (args.length == 1) {
                            ProxiedPlayer t = BungeeCord.getInstance().getPlayer(args[0]);
                            if (t != null) {
                                if (TeamStats.ts.existsAccount(t.getUniqueId())) {
                                    p.sendMessage("§7Statistiken von §9"+t.getDisplayName()+"§7:\n" +
                                            "§7Banns: §c"+TeamStats.ts.getBans(t.getUniqueId())+"\n" +
                                            "§7Mutes: §c"+TeamStats.ts.getMutes(t.getUniqueId())+"\n" +
                                            "§7Reports: §c"+TeamStats.ts.getReports(t.getUniqueId()));
                                }else{
                                    p.sendMessage(Main.main.prefix+"§7Er hat noch keinen Account");
                                }
                            }else{
                                OfflinePlayer ot = CloudAPI.getInstance().getOfflinePlayer(args[0]);
                                if (ot != null) {

                                    if (TeamStats.ts.existsAccount(ot.getUniqueId())) {
                                        p.sendMessage("§7Statistiken von §9"+ot.getName()+"§7:\n" +
                                                "§7Banns: §c"+TeamStats.ts.getBans(ot.getUniqueId())+"\n" +
                                                "§7Mutes: §c"+TeamStats.ts.getMutes(ot.getUniqueId())+"\n" +
                                                "§7Reports: §c"+TeamStats.ts.getReports(ot.getUniqueId()));
                                    }else{
                                        p.sendMessage(Main.main.prefix+"§7Er hat noch keinen Account");
                                    }

                                }else{
                                    p.sendMessage(Main.main.prefix+"§7Der Spieler existiert nicht!");
                                }
                            }
                        }

                    }else{
                        p.sendMessage(Main.main.noPerm);
                    }
                }
            });
        }
    }
}
