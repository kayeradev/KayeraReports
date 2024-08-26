package uk.kayera.kayeraReports;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

import java.util.Map;

public class ReportRemove implements CommandExecutor {

    private final Map<Integer, KayeraReports.ReportEntry> reportMap;

    public ReportRemove(Map<Integer, KayeraReports.ReportEntry> reportMap) {
        this.reportMap = reportMap;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("reportremove")) {
            if (args.length == 1) {
                try {
                    int reportId = Integer.parseInt(args[0]);

                    if (reportMap.containsKey(reportId)) {
                        reportMap.remove(reportId);
                        sender.sendMessage(String.format("§x§0§8§F§B§8§DK§x§1§D§F§B§9§7a§x§3§1§F§C§A§0y§x§4§6§F§C§A§Ae§x§5§A§F§C§B§3r§x§6§F§F§D§B§Da§x§8§4§F§D§C§6R§x§9§8§F§D§D§0e§x§A§D§F§E§D§9p§x§C§1§F§E§E§3o§x§D§6§F§E§E§Cr§x§E§A§F§F§F§6t§x§F§F§F§F§F§Fs §8► §fReport ID %d has been removed.", reportId));
                    } else {
                        sender.sendMessage(String.format("§x§0§8§F§B§8§DK§x§1§D§F§B§9§7a§x§3§1§F§C§A§0y§x§4§6§F§C§A§Ae§x§5§A§F§C§B§3r§x§6§F§F§D§B§Da§x§8§4§F§D§C§6R§x§9§8§F§D§D§0e§x§A§D§F§E§D§9p§x§C§1§F§E§E§3o§x§D§6§F§E§E§Cr§x§E§A§F§F§F§6t§x§F§F§F§F§F§Fs §8► §fNo report found with ID %d.", reportId));
                    }
                } catch (NumberFormatException e) {
                    sender.sendMessage("§x§0§8§F§B§8§DK§x§1§D§F§B§9§7a§x§3§1§F§C§A§0y§x§4§6§F§C§A§Ae§x§5§A§F§C§B§3r§x§6§F§F§D§B§Da§x§8§4§F§D§C§6R§x§9§8§F§D§D§0e§x§A§D§F§E§D§9p§x§C§1§F§E§E§3o§x§D§6§F§E§E§Cr§x§E§A§F§F§F§6t§x§F§F§F§F§F§Fs §8► §fInvalid report ID format.");
                }
            } else {
                sender.sendMessage("§x§0§8§F§B§8§DK§x§1§D§F§B§9§7a§x§3§1§F§C§A§0y§x§4§6§F§C§A§Ae§x§5§A§F§C§B§3r§x§6§F§F§D§B§Da§x§8§4§F§D§C§6R§x§9§8§F§D§D§0e§x§A§D§F§E§D§9p§x§C§1§F§E§E§3o§x§D§6§F§E§E§Cr§x§E§A§F§F§F§6t§x§F§F§F§F§F§Fs §8► §fCorrect usage: /reportremove (report-id)");
            }
        }
        return true;
    }
}