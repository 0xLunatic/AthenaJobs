package lunatic.athenajobs.commands;

import lunatic.athenajobs.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JobsSelectorCommand implements CommandExecutor {

    private Main plugin;
    private final Map<UUID, Long> cooldowns = new HashMap<>();
    public JobsSelectorCommand(Main plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }
        if (args.length <= 0){
            sender.sendMessage("§7§n--------------- §e§lAthena Jobs §7§n---------------");
            sender.sendMessage("");
            sender.sendMessage("§fFirst Jobs : §dWorker");
            sender.sendMessage("§7Level Requirement : §d[25] §7➜ §f[50] §7➜ §e[100] §7➜ §c[150]");
            sender.sendMessage("");
            sender.sendMessage("§7• §fBrewer §7➜ §eMaster Brewer §7➜ §cAlchemical Distiller");
            sender.sendMessage("§7• §fCrafter §7➜ §eArtisan §7➜ §cMaster Artisan");
            sender.sendMessage("§7• §fDigger §7➜ §eExcavator §7➜ §cEarthshaper");
            sender.sendMessage("§7• §fEnchanter §7➜ §eGlyphweaver §7➜ §cArcane Artisan");
            sender.sendMessage("§7• §fFarmer §7➜ §eAgriculturist §7➜ §cHarvestmaster");
            sender.sendMessage("§7• §fFisherman §7➜ §eMaster Angler §7➜ §cAquatic Maestro");
            sender.sendMessage("§7• §fHunter §7➜ §eScout §7➜ §cRanger");
            sender.sendMessage("§7• §fMiner §7➜ §eDighunter §7➜ §cVeinmaster");
            sender.sendMessage("§7• §fWeaponsmith §7➜ §eMaster Smith §7➜ §cLegendary Forgemaster");
            sender.sendMessage("§7• §fLumberjack §7➜ §eTimberer §7➜ §cArborist");
            sender.sendMessage("§7• §fKiller §7➜ §eVillain §7➜ §cPsychopath");
            sender.sendMessage("");
            sender.sendMessage("§c/jobs join [jobsName]");
            sender.sendMessage("");
            sender.sendMessage("§7§n--------------------------------------------");
        }
        else {
            if (args[0].equalsIgnoreCase("1")) {
                Player p = (Player) sender;
                if (!isOnCooldown(p)) {
                    setCooldown(p);
                    Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                        @Override
                        public void run() {
                            sender.sendMessage("§a[Jacob] §7: §fOh! Halo §e" + sender.getName() + "§f! Perkenalkan namaku Jacob!");
                            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 100, 1);
                            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                                @Override
                                public void run() {
                                    sender.sendMessage("§a[Jacob] §7: §fAku akan memberikan informasi terkait pekerjaan disini. Kamu dapat bekerja pertama kali sebagai §dWorker§f!");
                                    p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 100, 1);
                                    Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                                        @Override
                                        public void run() {
                                            sender.sendMessage("§a[Jacob] §7: §fPekerjaan itu cocok untuk pendatang baru! Aku juga akan memberitahu bahwa Pekerjaan disini mempunyai §eTier §ftersendiri.");
                                            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 100, 1);
                                            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                                                @Override
                                                public void run() {
                                                    sender.sendMessage("§a[Jacob] §7: §fPekerjaan tersebut memiliki §e3 Tier §fdisini. Setelah mencapai §dLevel 25 §fpada pekerjaan §aWorker §fmaka kamu dapat memilih pekerjaan selanjutnya!");
                                                    p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 100, 1);
                                                    Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            sender.sendMessage("§a[Jacob] §7: §fUntuk informasi pekerjaan apa saja yang ada disini, kamu dapat menggunakan command §c/helpjobs §f!");
                                                            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 100, 1);
                                                            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    sender.sendMessage("§a[Jacob] §7: §fAku pikir hanya itu saja informasi yang dapat aku berikan untuk sekarang...");
                                                                    p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 100, 1);
                                                                    Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                                                                        @Override
                                                                        public void run() {
                                                                            sender.sendMessage("§a[Jacob] §7: §fSelamat Bekerja!");
                                                                            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 100, 1);
                                                                            resetCooldown(p);
                                                                        }
                                                                    }, 40L); // 1 second delay before the last message
                                                                }
                                                            }, 40L); // 1 second delay
                                                        }
                                                    }, 40L); // 1 second delay
                                                }
                                            }, 40L); // 1 second delay
                                        }
                                    }, 40L); // 1 second delay
                                }
                            }, 40L); // 1 second delay
                        }
                    }, 10L); // Initial 1 second delay before the first message
                }
            }
        }

        return true;
    }
    private boolean isOnCooldown(Player player) {
        if (cooldowns.containsKey(player.getUniqueId())) {
            long lastTimeUsed = cooldowns.get(player.getUniqueId());
            long currentTime = System.currentTimeMillis() / 1000; // Convert to seconds
            int cooldownTime = 10; // Adjust the cooldown time in seconds

            return currentTime - lastTimeUsed < cooldownTime;
        }
        return false;
    }

    private void setCooldown(Player player) {
        cooldowns.put(player.getUniqueId(), System.currentTimeMillis() / 1000);
    }

    private void resetCooldown(Player player) {
        cooldowns.remove(player.getUniqueId());
    }

}
