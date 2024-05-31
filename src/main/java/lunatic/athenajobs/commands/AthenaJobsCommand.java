package lunatic.athenajobs.commands;

import lunatic.athenajobs.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class AthenaJobsCommand implements CommandExecutor {
    private Main plugin;

    public AthenaJobsCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (!(sender instanceof Player) || sender.hasPermission("athenajobs.reload")) {
                plugin.reloadConfig();
                plugin.config.getConfig("shopGui.yml").reload();
                sender.sendMessage(ChatColor.GREEN + "AthenaJobs configuration reloaded successfully.");
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                return true;
            }
        }

        sender.sendMessage(ChatColor.RED + "Usage: /athenajobs reload");
        return true;
    }
}
