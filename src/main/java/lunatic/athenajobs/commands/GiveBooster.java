package lunatic.athenajobs.commands;

import lunatic.athenajobs.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GiveBooster implements CommandExecutor {
    private Main plugin;

    public GiveBooster(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by a player.");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage("Usage: /givebooster <targetPlayer> <name>");
            return true;
        }

        if (args[1].equalsIgnoreCase("all")) {
            int duration = Integer.parseInt(args[2]);

            // If args[3] is a valid integer, perform the command
            ((Player) sender).getPlayer().performCommand("givebooster " + sender.getName() + " &d10% Jobs Money Booster &7(&b" + duration + " Minutes&7)");
            ((Player) sender).getPlayer().performCommand("givebooster " + sender.getName() + " &d20% Jobs Money Booster &7(&b" + duration + " Minutes&7)");
            ((Player) sender).getPlayer().performCommand("givebooster " + sender.getName() + " &d30% Jobs Money Booster &7(&b" + duration + " Minutes&7)");
            ((Player) sender).getPlayer().performCommand("givebooster " + sender.getName() + " &d40% Jobs Money Booster &7(&b" + duration + " Minutes&7)");
            ((Player) sender).getPlayer().performCommand("givebooster " + sender.getName() + " &d50% Jobs Money Booster &7(&b" + duration + " Minutes&7)");
            ((Player) sender).getPlayer().performCommand("givebooster " + sender.getName() + " &d60% Jobs Money Booster &7(&b" + duration + " Minutes&7)");
            ((Player) sender).getPlayer().performCommand("givebooster " + sender.getName() + " &d70% Jobs Money Booster &7(&b" + duration + " Minutes&7)");
            ((Player) sender).getPlayer().performCommand("givebooster " + sender.getName() + " &d80% Jobs Money Booster &7(&b" + duration + " Minutes&7)");
            ((Player) sender).getPlayer().performCommand("givebooster " + sender.getName() + " &d90% Jobs Money Booster &7(&b" + duration + " Minutes&7)");
            ((Player) sender).getPlayer().performCommand("givebooster " + sender.getName() + " &d100% Jobs Money Booster &7(&b" + duration + " Minutes&7)");

            ((Player) sender).getPlayer().performCommand("givebooster " + sender.getName() + " &d10% Jobs EXP Booster &7(&b" + duration + " Minutes&7)");
            ((Player) sender).getPlayer().performCommand("givebooster " + sender.getName() + " &d20% Jobs EXP Booster &7(&b" + duration + " Minutes&7)");
            ((Player) sender).getPlayer().performCommand("givebooster " + sender.getName() + " &d30% Jobs EXP Booster &7(&b" + duration + " Minutes&7)");
            ((Player) sender).getPlayer().performCommand("givebooster " + sender.getName() + " &d40% Jobs EXP Booster &7(&b" + duration + " Minutes&7)");
            ((Player) sender).getPlayer().performCommand("givebooster " + sender.getName() + " &d50% Jobs EXP Booster &7(&b" + duration + " Minutes&7)");
            ((Player) sender).getPlayer().performCommand("givebooster " + sender.getName() + " &d60% Jobs EXP Booster &7(&b" + duration + " Minutes&7)");
            ((Player) sender).getPlayer().performCommand("givebooster " + sender.getName() + " &d70% Jobs EXP Booster &7(&b" + duration + " Minutes&7)");
            ((Player) sender).getPlayer().performCommand("givebooster " + sender.getName() + " &d80% Jobs EXP Booster &7(&b" + duration + " Minutes&7)");
            ((Player) sender).getPlayer().performCommand("givebooster " + sender.getName() + " &d90% Jobs EXP Booster &7(&b" + duration + " Minutes&7)");
            ((Player) sender).getPlayer().performCommand("givebooster " + sender.getName() + " &d100% Jobs EXP Booster &7(&b" + duration + " Minutes&7)");

        } else {
            Player targetPlayer = sender.getServer().getPlayer(args[0]);
            if (targetPlayer == null || !targetPlayer.isOnline()) {
                sender.sendMessage("Player not found or not online.");
                return true;
            }

            // Check if args length is at least 6 before accessing args[5]
            if (args.length >= 5) {
                String boosterName = ChatColor.translateAlternateColorCodes('&', String.join(" ", Arrays.copyOfRange(args, 1, args.length)));

                ItemStack dragonBreath = new ItemStack(Material.DRAGON_BREATH);
                ItemMeta meta = dragonBreath.getItemMeta();

                meta.setDisplayName(boosterName);

                // Set lore
                List<String> lore = new ArrayList<>();
                lore.add("§fBoost up §bJobs Gain §fby §e" + args[1].replace("&d", "") + "§f for §b" + args[5].replaceAll("&7\\(&b", "").replaceAll("\\)", "")
                        + " minutes§f.");
                lore.add("§fRight-click to activate this booster and enhance");
                lore.add("§fall players' job gains. §eExpires after use.");
                lore.add("");
                lore.add("§cTHIS ITEM APPLIES TO ALL PLAYERS");

                meta.setLore(lore);
                dragonBreath.setItemMeta(meta);

                // Give the item to the player
                targetPlayer.getInventory().addItem(dragonBreath);

                sender.sendMessage("§aBooster given to " + targetPlayer.getName());
                return true;
            } else {
                sender.sendMessage("Insufficient arguments. Usage: /givebooster <targetPlayer> <name>");
                return true;
            }
        }
        return false;
    }
}
