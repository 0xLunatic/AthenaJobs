package lunatic.athenajobs.commands.prestige;

import lunatic.athenajobs.Main;
import lunatic.athenajobs.data.FileManager;
import lunatic.athenajobs.event.OnJobsJoin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PrestigeCommand implements CommandExecutor {
    private Main plugin;
    private OnJobsJoin jobs;
    private ShopGUI shopGUI;

    public PrestigeCommand(Main plugin) {
        this.plugin = plugin;
        jobs = new OnJobsJoin(plugin);
        shopGUI = new ShopGUI(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("athenajobs.prestige")) {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        if (args.length == 0) {
            // Default prestige logic
            if (jobs.isEnoughLevel(player, 150)) {
                Bukkit.dispatchCommand(player, "jobs leaveall");
                Bukkit.dispatchCommand(player, "jobs leaveall");
                jobs.playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                plugin.dbConnection.addPlayer(player.getUniqueId(), player.getName());
                plugin.dbConnection.addCoins(player.getUniqueId(), 1);
                plugin.dbConnection.addLevel(player.getUniqueId(), 1);
                Bukkit.broadcastMessage("\n§e§lJobs » §d" + player.getName() + " §fbaru saja melakukan §e§lPrestige §fke-"+ plugin.dbConnection.getLevel(player.getUniqueId()) + " dan mendapatkan §e§l1 Prestige Coin§f!\n");

                plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + player.getName() + " permission set jobs.boost.all.exp.0." + plugin.dbConnection.getLevel(player.getUniqueId()));
                plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + player.getName() + " permission set jobs.boost.all.money.0." + plugin.dbConnection.getLevel(player.getUniqueId()));
            } else {
                player.sendMessage("§cYou don't have enough level to prestige! Required level 150!");
            }
        } else if (args.length == 1 && args[0].equalsIgnoreCase("shop")) {
            // Open shop GUI
            shopGUI.openShop(player);
        } else {
            player.sendMessage(ChatColor.RED + "Invalid command usage.");
        }

        return true;
    }
}
