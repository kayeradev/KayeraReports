package uk.kayera.kayeraReports;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class KayeraReports extends JavaPlugin implements Listener {

    private Map<Integer, ReportEntry> reportMap = new HashMap<>();
    private int reportId = 0;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("report").setExecutor(this);
        getCommand("reportlist").setExecutor(new ReportList(reportMap));
        getCommand("reportremove").setExecutor(new ReportRemove(reportMap));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("report")) {
            if (args.length > 1) {
                String reportedPlayer = args[0];
                String message = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                String reporter = sender.getName();

                ReportEntry reportEntry = new ReportEntry(reporter, reportedPlayer, message);
                reportMap.put(++reportId, reportEntry);

                sender.sendMessage("§x§0§8§F§B§8§DK§x§1§D§F§B§9§7a§x§3§1§F§C§A§0y§x§4§6§F§C§A§Ae§x§5§A§F§C§B§3r§x§6§F§F§D§B§Da§x§8§4§F§D§C§6R§x§9§8§F§D§D§0e§x§A§D§F§E§D§9p§x§C§1§F§E§E§3o§x§D§6§F§E§E§Cr§x§E§A§F§F§F§6t§x§F§F§F§F§F§Fs §8► §fPlayer §x§0§C§F§F§A§D" + reportedPlayer + "§f reported successfully with reason: §x§0§C§F§F§A§D" + message);

                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    if (player.hasPermission("kayera.reportadmin")) {
                        player.sendMessage("§x§0§8§F§B§8§DK§x§1§D§F§B§9§7a§x§3§1§F§C§A§0y§x§4§6§F§C§A§Ae§x§5§A§F§C§B§3r§x§6§F§F§D§B§Da§x§8§4§F§D§C§6R§x§9§8§F§D§D§0e§x§A§D§F§E§D§9p§x§C§1§F§E§E§3o§x§D§6§F§E§E§Cr§x§E§A§F§F§F§6t§x§F§F§F§F§F§Fs §8► §fPlayer §x§0§C§F§F§A§D" + player.getName() + "§f submitted a new report for §x§0§C§F§F§A§D" + reportedPlayer + "§f with reason §x§0§C§F§F§A§D" + message);
                    }
                }
            } else {
                sender.sendMessage("§x§0§8§F§B§8§DK§x§1§D§F§B§9§7a§x§3§1§F§C§A§0y§x§4§6§F§C§A§Ae§x§5§A§F§C§B§3r§x§6§F§F§D§B§Da§x§8§4§F§D§C§6R§x§9§8§F§D§D§0e§x§A§D§F§E§D§9p§x§C§1§F§E§E§3o§x§D§6§F§E§E§Cr§x§E§A§F§F§F§6t§x§F§F§F§F§F§Fs §8► §fCorrect usage: /report (player) (message)");
            }
        }
        return true;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Map<Integer, ReportEntry> getReportMap() {
        return reportMap;
    }

    public static class ReportEntry {
        private final String reporter;
        private final String reportedPlayer;
        private final String message;

        public ReportEntry(String reporter, String reportedPlayer, String message) {
            this.reporter = reporter;
            this.reportedPlayer = reportedPlayer;
            this.message = message;
        }

        public String getReporter() {
            return reporter;
        }

        public String getReportedPlayer() {
            return reportedPlayer;
        }

        public String getMessage() {
            return message;
        }
    }
}