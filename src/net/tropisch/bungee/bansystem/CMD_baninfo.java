package net.tropisch.bungee.bansystem;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.OfflinePlayer;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.tropisch.bungee.main.Main;

public class CMD_baninfo extends Command {
    public CMD_baninfo() {
        super("baninfo", null, new String[]{"bi, banlog, checkban"});
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if (cs instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) cs;
            BungeeCord.getInstance().getScheduler().runAsync(Main.main, new Runnable() {
                @Override
                public void run() {
                    if (p.hasPermission("system.baninfo")) {

                        if (args.length == 1) {

                            ProxiedPlayer t = BungeeCord.getInstance().getPlayer(args[0]);
                            if (t == null) {

                                OfflinePlayer ot = CloudAPI.getInstance().getOfflinePlayer(args[0]);
                                if (ot != null) {

                                    if (BanSystem.bs.isBanned(ot.getUniqueId())) {

                                        p.sendMessage("\n§f==========================================\n" +
                                                "§7Von: §c"+BanSystem.bs.getBanner(ot.getUniqueId())+"\n" +
                                                "§7Grund: §c"+BanSystem.bs.getReason(ot.getUniqueId())+"\n"+
                                                "§7Datum: §c"+BanSystem.bs.getDate(ot.getUniqueId())+"\n" +
                                                "§7Replay-ID: §c"+BanSystem.bs.getReplay(ot.getUniqueId())+"\n"+
                                                "§7Verbleibende Zeit: §c"+BanSystem.bs.getRemainingTime(ot.getUniqueId())+"\n" +
                                                "§f==========================================\n");

                                    }else{
                                        p.sendMessage(Main.main.prefix+"§7Der Spieler ist nicht gebannt!");
                                    }

                                }else{
                                    p.sendMessage(Main.main.prefix+"§7Der Spieler existiert nicht!");
                                }

                            }else{
                                p.sendMessage(Main.main.prefix+"§7Der Spieler ist nicht gebannt!");
                            }

                        }else{
                            p.sendMessage(Main.main.prefix+"§7Bitte gib einen Spieler an");
                        }

                    }else{
                        p.sendMessage(Main.main.noPerm);
                    }
                }
            });
        }
    }
}
