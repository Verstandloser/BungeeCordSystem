package net.tropisch.bungee.bansystem;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.OfflinePlayer;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.tropisch.bungee.main.Main;

public class CMD_mute extends Command {
    public CMD_mute() {
        super("mute");
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if (cs instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) cs;
            BungeeCord.getInstance().getScheduler().runAsync(Main.main, new Runnable() {
                @Override
                public void run() {
                    if (p.hasPermission("system.mute")) {

                        if (args.length == 2) {

                            ProxiedPlayer t = BungeeCord.getInstance().getPlayer(args[0]);
                            if (t != null) {

                                if (!MuteSystem.bs.isMuted(t.getUniqueId())) {

                                    if (args[1].equalsIgnoreCase("1")) {
                                        long l = 3600000;
                                        MuteSystem.bs.onMute(t.getUniqueId(), p.getUniqueId(), "Chatverhalten", "Nicht angegeben",
                                                l);
                                    } else if (args[1].equalsIgnoreCase("2")) {
                                        long l = 10800000;
                                        MuteSystem.bs.onMute(t.getUniqueId(), p.getUniqueId(), "Chatverhalten", "Nicht angegeben",
                                                l);
                                    } else if (args[1].equalsIgnoreCase("3")) {
                                        long l = 86400000;
                                        MuteSystem.bs.onMute(t.getUniqueId(), p.getUniqueId(), "Chatverhalten", "Nicht angegeben",
                                                l);
                                    } else if (args[1].equalsIgnoreCase("4")) {
                                        long l = 1296000000;
                                        MuteSystem.bs.onMute(t.getUniqueId(), p.getUniqueId(), "Werbung", "Nicht angegeben",
                                                l);
                                    } else if (args[1].equalsIgnoreCase("5")) {
                                        long l = -1;
                                        MuteSystem.bs.onMute(t.getUniqueId(), p.getUniqueId(), "Muteumgehung", "Nicht angegeben",
                                                l);
                                    } else if (args[1].equalsIgnoreCase("6")) {
                                        long l = 1;
                                        MuteSystem.bs.onMute(t.getUniqueId(), p.getUniqueId(), "TestMute", "Nicht angegeben",
                                                l);
                                    }else{
                                        p.sendMessage(MuteSystem.bs.getMuteMSG());
                                    }

                                }else{
                                    p.sendMessage(Main.main.prefix+"§7Der Spieler ist bereits gemutet!");
                                }

                            }else{
                                OfflinePlayer ot = CloudAPI.getInstance().getOfflinePlayer(args[0]);
                                if (ot != null) {

                                    if (!MuteSystem.bs.isMuted(ot.getUniqueId())) {

                                        if (args[1].equalsIgnoreCase("1")) {
                                            long l = 1;
                                            MuteSystem.bs.onMute(ot.getUniqueId(), p.getUniqueId(), "Chatverhalten", "Nicht angegeben",
                                                    l);
                                        } else if (args[1].equalsIgnoreCase("2")) {
                                            long l = 1;
                                            MuteSystem.bs.onMute(ot.getUniqueId(), p.getUniqueId(), "Chatverhalten", "Nicht angegeben",
                                                    l);
                                        } else if (args[1].equalsIgnoreCase("3")) {
                                            long l = 1;
                                            MuteSystem.bs.onMute(ot.getUniqueId(), p.getUniqueId(), "Chatverhalten", "Nicht angegeben",
                                                    l);
                                        } else if (args[1].equalsIgnoreCase("4")) {
                                            long l = 1;
                                            MuteSystem.bs.onMute(ot.getUniqueId(), p.getUniqueId(), "Werbung", "Nicht angegeben",
                                                    l);
                                        } else if (args[1].equalsIgnoreCase("5")) {
                                            long l = -1;
                                            MuteSystem.bs.onMute(ot.getUniqueId(), p.getUniqueId(), "Muteumgehung", "Nicht angegeben",
                                                    l);
                                        }else{
                                            p.sendMessage(MuteSystem.bs.getMuteMSG());
                                        }

                                    }else{
                                        p.sendMessage(Main.main.prefix+"§7Der Spieler ist bereits gemutet!");
                                    }

                                }else{
                                    p.sendMessage(Main.main.prefix+"§7Der Spieler existiert nicht!");
                                }
                            }

                        } else if (args.length == 3) {



                        }else{
                            p.sendMessage(MuteSystem.bs.getMuteMSG());
                        }

                    }else{
                        p.sendMessage(Main.main.noPerm);
                    }
                }
            });
        }
    }
}
