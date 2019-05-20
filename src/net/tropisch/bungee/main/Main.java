package net.tropisch.bungee.main;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.tropisch.bungee.bansystem.*;
import net.tropisch.bungee.commands.*;
import net.tropisch.bungee.events.ChatEvents;
import net.tropisch.bungee.events.JoinEvents;
import net.tropisch.bungee.events.QuitEvents;
import net.tropisch.bungee.methods.MySQL;
import net.tropisch.bungee.reportsystem.CMD_report;
import net.tropisch.bungee.reportsystem.ReportSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main extends Plugin {

    public static Main main;

    public String prefix = "§c[§fTropisch.net§c] ";
    public String noPerm = prefix+"§cDu besitzt nicht ausreichend Rechte!";

    public ArrayList<ProxiedPlayer> teamnotify = new ArrayList<>();

    public HashMap<String, String> chatlog = new HashMap<>();

    public List<String> blockedWords = new ArrayList<>();

    @Override
    public void onEnable() {

        broadcastReport();
        autoBroadcast();

        registerCommands();
        registerEvents();
        fillBlockedWords();

        MySQL.sql.connect();

        main = this;
    }

    @Override
    public void onDisable() {
        for(ProxiedPlayer a : BungeeCord.getInstance().getPlayers()){
            a.disconnect("§cDein BungeeCord-Server wurde gestoppt!");
        }
    }

    private void registerCommands(){
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new CMD_bc());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new CMD_cc());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new CMD_ping());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new CMD_jumpto());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new CMD_find());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new CMD_notify());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new CMD_tc());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new CMD_report());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new CMD_chatlog());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new CMD_joinme());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new CMD_tstats());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new CMD_bstop());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new CMD_sstop());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new CMD_nstop());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new CMD_stop());
        // START BAN SECTION
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new CMD_ban());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new CMD_unban());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new CMD_baninfo());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new CMD_kick());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new CMD_mute());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new CMD_unmute());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new CMD_muteinfo());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new CMD_editmute());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new CMD_editban());
        // END BAN SECTION
    }

    private void registerEvents(){
        BungeeCord.getInstance().getPluginManager().registerListener(this, new JoinEvents());
        BungeeCord.getInstance().getPluginManager().registerListener(this, new ChatEvents());
        BungeeCord.getInstance().getPluginManager().registerListener(this, new QuitEvents());
    }

    private void fillBlockedWords() {
        blockedWords.add("HURENSOHN");
        blockedWords.add("BASTARD");
        blockedWords.add("HURE");
        blockedWords.add("HUSO");
        blockedWords.add("HUSP");
        blockedWords.add("HUS");
        blockedWords.add("HS");
        blockedWords.add("HITLER");
        blockedWords.add("HEIL");
        blockedWords.add("SIEG");
        blockedWords.add("NIGGA");
        blockedWords.add("IDIOT");
        blockedWords.add("ARSCH");
        blockedWords.add("ARSCHLOCH");
        blockedWords.add("CYKA");
        blockedWords.add(".DE");
        blockedWords.add(".EU");
        blockedWords.add(".NET");
        blockedWords.add(".ML");
    }

    private void broadcastReport(){
        BungeeCord.getInstance().getScheduler().schedule(this, new Runnable() {
            @Override
            public void run() {
                broadcastReport();
                if (ReportSystem.getInstance().reportlist.size() == 1) {
                    for(ProxiedPlayer a : Main.main.teamnotify){
                        if (a.hasPermission("system.report")) {
                            net.md_5.bungee.api.chat.TextComponent tc = new TextComponent(Main.main.prefix+"§7Derzeit ist §c" +
                                    ReportSystem.getInstance().reportlist.size()+" §7Report offen!");
                            tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Bearbeite diesen Report").create()));
                            tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/report auto"));
                            a.sendMessage(tc);
                        }
                    }
                }else if (ReportSystem.getInstance().reportlist.size() > 1) {
                    for(ProxiedPlayer a : Main.main.teamnotify){
                        if (a.hasPermission("system.report")) {
                            net.md_5.bungee.api.chat.TextComponent tc = new TextComponent(Main.main.prefix+"§7Derzeit sind §c" +
                                    ReportSystem.getInstance().reportlist.size()+" §7Reports offen!");
                            tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Bearbeite einen Report").create()));
                            tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/report auto"));
                            a.sendMessage(tc);
                        }
                    }
                }
            }
        }, 1, TimeUnit.MINUTES);
    }

    private int id = 0;

    private void autoBroadcast(){
        BungeeCord.getInstance().getScheduler().schedule(this, new Runnable() {
            @Override
            public void run() {
                autoBroadcast();

                if (id == 0) {
                    BungeeCord.getInstance().broadcast("" +
                            "\n\n\n§c====================================\n\n" +
                            "§7Du hast einen Regelbrecher gefunden?\n" +
                            "§7Du kannst diesen mit §f/report melden\n\n" +
                            "§c====================================\n\n\n");
                    id = id+1;
                }else if (id == 1) {
                    BungeeCord.getInstance().broadcast("" +
                            "\n\n\n§c====================================\n\n" +
                            "§7Du findest unseren TeamSpeak unter:\n" +
                            "§fTropisch.net\n\n" +
                            "§c====================================\n\n\n");
                    id = id+1;
                }else if (id == 2) {
                    BungeeCord.getInstance().broadcast("" +
                            "\n\n\n§c====================================\n\n" +
                            "§7Du findest unser Forum unter:\n" +
                            "§fTropisch.net/forum\n\n" +
                            "§c====================================\n\n\n");
                    id = id+1;
                }else if (id == 3) {
                    BungeeCord.getInstance().broadcast("" +
                            "\n\n\n§c====================================\n\n" +
                            "§7Du möchtest uns unterstützen?\n" +
                            "§7Schau doch mal im Shop vorbei\n" +
                            "§fshop.tropisch.net\n\n" +
                            "§c====================================\n\n\n");
                    id = id+1;
                }else if (id == 4) {
                    BungeeCord.getInstance().broadcast("" +
                            "\n\n\n§c====================================\n\n" +
                            "§7Du kannst dich derzeit bewerben!\n" +
                            "§7Weiteres hier: §fTropisch.net/apply\n\n" +
                            "§c====================================\n\n\n");
                    id = id+1;
                }else if (id == 5) {
                    BungeeCord.getInstance().broadcast("" +
                            "\n\n\n§c====================================\n\n" +
                            "§7Wir sind im §4Aufbau!\n\n" +
                            "§c====================================\n\n\n");
                    id = id+1;
                }else if (id == 6) {
                    BungeeCord.getInstance().broadcast("" +
                            "\n\n\n§c====================================\n\n" +
                            "§7Regeln findest du hier:!\n" +
                            "§fregeln.tropisch.net\n\n" +
                            "§c====================================\n\n\n");
                    id = 0;
                }
            }
        }, 5, TimeUnit.MINUTES);
    }
}
