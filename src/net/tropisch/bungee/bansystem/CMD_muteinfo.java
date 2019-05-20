package net.tropisch.bungee.bansystem;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.OfflinePlayer;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.tropisch.bungee.main.Main;

public class CMD_muteinfo extends Command {
    public CMD_muteinfo() {
        super("muteinfo");
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if (cs instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) cs;
            BungeeCord.getInstance().getScheduler().runAsync(Main.main, new Runnable() {
                @Override
                public void run() {
                    if (p.hasPermission("system.muteinfo")) {

                        if (args.length == 1) {

                            ProxiedPlayer t = BungeeCord.getInstance().getPlayer(args[0]);
                            if (t != null) {

                                if (MuteSystem.bs.isMuted(t.getUniqueId())) {

                                    p.sendMessage("\n§f==========================================\n" +
                                            "§7Von: §c"+MuteSystem.bs.getBanner(t.getUniqueId())+"\n" +
                                            "§7Grund: §c"+MuteSystem.bs.getReason(t.getUniqueId())+"\n"+
                                            "§7Datum: §c"+MuteSystem.bs.getDate(t.getUniqueId())+"\n" +
                                            "§7Beweis: §c"+MuteSystem.bs.getBeweis(t.getUniqueId())+"\n"+
                                            "§7Verbleibende Zeit: §c"+MuteSystem.bs.getRemainingTime(t.getUniqueId())+"\n" +
                                            "§f==========================================\n");

                                }else{
                                    p.sendMessage(Main.main.prefix+"§7Der Spieler ist nicht gemutet!");
                                }

                            }else{
                                OfflinePlayer ot = CloudAPI.getInstance().getOfflinePlayer(args[0]);
                                if (ot != null) {

                                    if (MuteSystem.bs.isMuted(ot.getUniqueId())) {

                                        p.sendMessage("\n§f==========================================\n" +
                                                "§7Von: §c"+MuteSystem.bs.getBanner(ot.getUniqueId())+"\n" +
                                                "§7Grund: §c"+MuteSystem.bs.getReason(ot.getUniqueId())+"\n"+
                                                "§7Datum: §c"+MuteSystem.bs.getDate(ot.getUniqueId())+"\n" +
                                                "§7Beweis: §c"+MuteSystem.bs.getBeweis(ot.getUniqueId())+"\n"+
                                                "§7Verbleibende Zeit: §c"+MuteSystem.bs.getRemainingTime(ot.getUniqueId())+"\n" +
                                                "§f==========================================\n");


                                    }else{
                                        p.sendMessage(Main.main.prefix+"§7Der Spieler ist nicht gemutet!");
                                    }

                                }else{
                                    p.sendMessage(Main.main.prefix+"§7Der Spieler existiert nicht!");
                                }
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
