package lunatic.athenajobs.commands;

import lunatic.athenajobs.Main;
import lunatic.athenajobs.event.UseBooster;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Terimakasih implements CommandExecutor {
    private Main plugin;
    UseBooster booster;

    // Map to store player cooldowns
    private final Map<UUID, Long> cooldowns = new HashMap<>();
    private static final long COOLDOWN_TIME = 60 * 1000; // 60 seconds

    public Terimakasih(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }

        Player player = (Player) sender;
        UUID playerId = player.getUniqueId();

        // Check cooldown
        if (cooldowns.containsKey(playerId)) {
            long timeLeft = (cooldowns.get(playerId) + COOLDOWN_TIME - System.currentTimeMillis()) / 1000;
            if (timeLeft > 0) {
                sender.sendMessage("§cYou must wait " + timeLeft + " seconds before using this command again.");
                return true;
            }
        }

        if (plugin.boosterIs != null) {
            if (player.getName() != null && !player.getName().equalsIgnoreCase(plugin.boosterIs)) {
                if (!plugin.boosterIs.equalsIgnoreCase("null") && plugin.economy.getBalance(player) > 100) {
                    plugin.economy.withdrawPlayer(player, 100);
                    plugin.economy.depositPlayer(plugin.boosterIs, 100);

                    Bukkit.broadcastMessage("§e" + player.getName() + " §fberterimakasih kepada §e" + plugin.boosterIs + "§f!");

                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        onlinePlayer.playSound(onlinePlayer, Sound.ENTITY_PLAYER_LEVELUP, 100, 2f);
                    }

                    // Set cooldown
                    cooldowns.put(playerId, System.currentTimeMillis());
                } else {
                    sender.sendMessage("§cFailed to execute command, not enough money or no active booster!");
                }
            } else {
                sender.sendMessage("§cCannot express gratitude to yourself!");
            }
        } else {
            sender.sendMessage("§cNo active booster!");
        }

        return true;
    }
}
