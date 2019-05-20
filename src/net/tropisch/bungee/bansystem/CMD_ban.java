package net.tropisch.bungee.bansystem;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.player.OfflinePlayer;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.tropisch.bungee.main.Main;

public class CMD_ban extends Command {
    public CMD_ban() {
        super("ban");
    }

    @Override
    public void execute(CommandSender cs, String[] args) {
        if (cs instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) cs;
            BungeeCord.getInstance().getScheduler().runAsync(Main.main, new Runnable() {
                @Override
                public void run() {
                    if (p.hasPermission("system.ban")) {

                        if (args.length == 2) {

                            ProxiedPlayer t = BungeeCord.getInstance().getPlayer(args[0]);
                            if (t != null) {

                                if (args[1].equalsIgnoreCase("1")) {
                                    long i = 592000000;
                                    long a = 2000000000;
                                    long e = a+i;
                                    BanSystem.bs.onBan(t.getUniqueId(), p.getUniqueId(), "unerlaubte Modifikationen", "nicht angegeben", e);
                                }else if (args[1].equalsIgnoreCase("2")) {
                                    long i = 432000000;
                                    BanSystem.bs.onBan(t.getUniqueId(), p.getUniqueId(), "Bugausnutzung", "nicht angegeben", i);
                                }else if (args[1].equalsIgnoreCase("3")) {
                                    long i = 259200000;
                                    BanSystem.bs.onBan(t.getUniqueId(), p.getUniqueId(), "unerlaubtes Teaming", "nicht angegeben", i);
                                }else if (args[1].equalsIgnoreCase("4")) {
                                    long i = 86400000;
                                    BanSystem.bs.onBan(t.getUniqueId(), p.getUniqueId(), "Trolling / Spawntrapping", "nicht angegeben", i);
                                }else if (args[1].equalsIgnoreCase("5")) {
                                    long i = -1;
                                    BanSystem.bs.onBan(t.getUniqueId(), p.getUniqueId(), "Werbung", "nicht angegeben", i);
                                }else if (args[1].equalsIgnoreCase("6")) {
                                    long i = 592000000;
                                    long a = 2000000000;
                                    long e = a+i;
                                    BanSystem.bs.onBan(t.getUniqueId(), p.getUniqueId(), "Namensgebung", "nicht angegeben", e);
                                }else if (args[1].equalsIgnoreCase("7")) {
                                    long i = 432000000;
                                    BanSystem.bs.onBan(t.getUniqueId(), p.getUniqueId(), "unangebrachter Skin", "nicht angegeben", i);
                                }else if (args[1].equalsIgnoreCase("8")) {
                                    long i = -1;
                                    BanSystem.bs.onBan(t.getUniqueId(), p.getUniqueId(), "Bannumgehung / Alt-Account", "nicht angegeben", i);
                                }else if (args[1].equalsIgnoreCase("9")) {
                                    if (p.hasPermission("system.ban.extrem")) {
                                        long i = -1;
                                        BanSystem.bs.onBan(t.getUniqueId(), p.getUniqueId(), "virtuelles Hausverbot", "nicht angegeben", i);
                                    }else{
                                        p.sendMessage(Main.main.prefix+"§7Du darfst diesen Grund nicht verwenden");
                                    }
                                }else{
                                    p.sendMessage(BanSystem.bs.getBanMSG());
                                }

                            }else{
                                OfflinePlayer ct = CloudAPI.getInstance().getOfflinePlayer(args[0]);
                                if (ct != null) {

                                    if (!BanSystem.bs.isBanned(ct.getUniqueId())) {
                                        if (args[1].equalsIgnoreCase("1")) {
                                            long i = 592000000;
                                            long a = 2000000000;
                                            long e = a+i;
                                            BanSystem.bs.onBan(ct.getUniqueId(), p.getUniqueId(), "unerlaubte Modifikationen", "nicht angegeben", e);
                                        }else if (args[1].equalsIgnoreCase("2")) {
                                            long i = 432000000;
                                            BanSystem.bs.onBan(ct.getUniqueId(), p.getUniqueId(), "Bugausnutzung", "nicht angegeben", i);
                                        }else if (args[1].equalsIgnoreCase("3")) {
                                            long i = 259200000;
                                            BanSystem.bs.onBan(ct.getUniqueId(), p.getUniqueId(), "unerlaubtes Teaming", "nicht angegeben", i);
                                        }else if (args[1].equalsIgnoreCase("4")) {
                                            long i = 86400000;
                                            BanSystem.bs.onBan(ct.getUniqueId(), p.getUniqueId(), "Trolling / Spawntrapping", "nicht angegeben", i);
                                        }else if (args[1].equalsIgnoreCase("5")) {
                                            long i = -1;
                                            BanSystem.bs.onBan(ct.getUniqueId(), p.getUniqueId(), "Werbung", "nicht angegeben", i);
                                        }else if (args[1].equalsIgnoreCase("6")) {
                                            long i = 592000000;
                                            long a = 2000000000;
                                            long e = a+i;
                                            BanSystem.bs.onBan(ct.getUniqueId(), p.getUniqueId(), "Namensgebung", "nicht angegeben", e);
                                        }else if (args[1].equalsIgnoreCase("7")) {
                                            long i = 432000000;
                                            BanSystem.bs.onBan(ct.getUniqueId(), p.getUniqueId(), "unangebrachter Skin", "nicht angegeben", i);
                                        }else if (args[1].equalsIgnoreCase("8")) {
                                            long i = -1;
                                            BanSystem.bs.onBan(ct.getUniqueId(), p.getUniqueId(), "Bannumgehung / Alt-Account", "nicht angegeben", i);
                                        }else if (args[1].equalsIgnoreCase("9")) {
                                            if (p.hasPermission("system.ban.extrem")) {
                                                long i = -1;
                                                BanSystem.bs.onBan(ct.getUniqueId(), p.getUniqueId(), "virtuelles Hausverbot", "nicht angegeben", i);
                                            }else{
                                                p.sendMessage(Main.main.prefix+"§7Du darfst diesen Grund nicht verwenden");
                                            }
                                        }else{
                                            p.sendMessage(BanSystem.bs.getBanMSG());
                                        }
                                    }else{
                                        p.sendMessage(Main.main.prefix+"§7Der Spieler ist bereits gebannt");
                                    }

                                }else{
                                    p.sendMessage(Main.main.prefix+"§7Der Spieler existiert nicht!");
                                }
                            }

                        } else if (args.length == 3) {

                            ProxiedPlayer t = BungeeCord.getInstance().getPlayer(args[0]);
                            if (t != null) {

                                if (args[1].equalsIgnoreCase("1")) {
                                    long i = 592000000;
                                    long a = 2000000000;
                                    long e = a+i;
                                    BanSystem.bs.onBan(t.getUniqueId(), p.getUniqueId(), "unerlaubte Modifikationen", args[2], e);
                                }else if (args[1].equalsIgnoreCase("2")) {
                                    long i = 432000000;
                                    BanSystem.bs.onBan(t.getUniqueId(), p.getUniqueId(), "Bugausnutzung", args[2], i);
                                }else if (args[1].equalsIgnoreCase("3")) {
                                    long i = 259200000;
                                    BanSystem.bs.onBan(t.getUniqueId(), p.getUniqueId(), "unerlaubtes Teaming", args[2], i);
                                }else if (args[1].equalsIgnoreCase("4")) {
                                    long i = 86400000;
                                    BanSystem.bs.onBan(t.getUniqueId(), p.getUniqueId(), "Trolling / Spawntrapping", args[2], i);
                                }else if (args[1].equalsIgnoreCase("5")) {
                                    long i = -1;
                                    BanSystem.bs.onBan(t.getUniqueId(), p.getUniqueId(), "Werbung", args[2], i);
                                }else if (args[1].equalsIgnoreCase("6")) {
                                    long i = 592000000;
                                    long a = 2000000000;
                                    long e = a+i;
                                    BanSystem.bs.onBan(t.getUniqueId(), p.getUniqueId(), "Namensgebung", args[2], e);
                                }else if (args[1].equalsIgnoreCase("7")) {
                                    long i = 432000000;
                                    BanSystem.bs.onBan(t.getUniqueId(), p.getUniqueId(), "unangebrachter Skin", args[2], i);
                                }else if (args[1].equalsIgnoreCase("8")) {
                                    long i = -1;
                                    BanSystem.bs.onBan(t.getUniqueId(), p.getUniqueId(), "Bannumgehung / Alt-Account", args[2], i);
                                }else if (args[1].equalsIgnoreCase("9")) {
                                    if (p.hasPermission("system.ban.extrem")) {
                                        long i = -1;
                                        BanSystem.bs.onBan(t.getUniqueId(), p.getUniqueId(), "virtuelles Hausverbot", args[2], i);
                                    }else{
                                        p.sendMessage(Main.main.prefix+"§7Du darfst diesen Grund nicht verwenden");
                                    }
                                }else{
                                    p.sendMessage(BanSystem.bs.getBanMSG());
                                }

                            }else{
                                OfflinePlayer ct = CloudAPI.getInstance().getOfflinePlayer(args[0]);
                                if (ct != null) {

                                    if (!BanSystem.bs.isBanned(ct.getUniqueId())) {
                                        if (args[1].equalsIgnoreCase("1")) {
                                            long i = 592000000;
                                            long a = 2000000000;
                                            long e = a+i;
                                            BanSystem.bs.onBan(ct.getUniqueId(), p.getUniqueId(), "unerlaubte Modifikationen", args[2], e);
                                        }else if (args[1].equalsIgnoreCase("2")) {
                                            long i = 432000000;
                                            BanSystem.bs.onBan(ct.getUniqueId(), p.getUniqueId(), "Bugausnutzung", args[2], i);
                                        }else if (args[1].equalsIgnoreCase("3")) {
                                            long i = 259200000;
                                            BanSystem.bs.onBan(ct.getUniqueId(), p.getUniqueId(), "unerlaubtes Teaming", args[2], i);
                                        }else if (args[1].equalsIgnoreCase("4")) {
                                            long i = 86400000;
                                            BanSystem.bs.onBan(ct.getUniqueId(), p.getUniqueId(), "Trolling / Spawntrapping", args[2], i);
                                        }else if (args[1].equalsIgnoreCase("5")) {
                                            long i = -1;
                                            BanSystem.bs.onBan(ct.getUniqueId(), p.getUniqueId(), "Werbung", args[2], i);
                                        }else if (args[1].equalsIgnoreCase("6")) {
                                            long i = 592000000;
                                            long a = 2000000000;
                                            long e = a+i;
                                            BanSystem.bs.onBan(ct.getUniqueId(), p.getUniqueId(), "Namensgebung", args[2], e);
                                        }else if (args[1].equalsIgnoreCase("7")) {
                                            long i = 432000000;
                                            BanSystem.bs.onBan(ct.getUniqueId(), p.getUniqueId(), "unangebrachter Skin", args[2], i);
                                        }else if (args[1].equalsIgnoreCase("8")) {
                                            long i = -1;
                                            BanSystem.bs.onBan(ct.getUniqueId(), p.getUniqueId(), "Bannumgehung / Alt-Account", args[2], i);
                                        }else if (args[1].equalsIgnoreCase("9")) {
                                            if (p.hasPermission("system.ban.extrem")) {
                                                long i = -1;
                                                BanSystem.bs.onBan(ct.getUniqueId(), p.getUniqueId(), "virtuelles Hausverbot", args[2], i);
                                            }else{
                                                p.sendMessage(Main.main.prefix+"§7Du darfst diesen Grund nicht verwenden");
                                            }
                                        }else{
                                            p.sendMessage(BanSystem.bs.getBanMSG());
                                        }
                                    }else{
                                        p.sendMessage(Main.main.prefix+"§7Der Spieler ist bereits gebannt");
                                    }

                                }else{
                                    p.sendMessage(Main.main.prefix+"§7Der Spieler existiert nicht!");
                                }
                            }

                        }else{
                            p.sendMessage(BanSystem.bs.getBanMSG());
                        }

                    }else{
                        p.sendMessage(Main.main.noPerm);
                    }
                }
            });
        }
    }
}
