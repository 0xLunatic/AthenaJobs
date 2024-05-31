package lunatic.athenajobs.event;

import lunatic.athenajobs.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class UseBooster implements Listener {
    private Main plugin;
    private final List<String> jobsArray = Arrays.asList(
            "Worker", "Brewer", "MasterBrewer", "AlchemicalDistiller", "Crafter", "Artisan",
            "MasterArtisan", "Digger", "Excavator", "Earthshaper", "Enchanter",
            "Glyphweaver", "ArcaneArtisan", "Farmer", "Agriculturist", "Harvestmaster",
            "Fisherman", "MasterAngler", "AquaticMaestro", "Hunter", "Scout",
            "Ranger", "Killer", "Villain", "Psychopath", "Lumberjack", "Timberer",
            "Arborist", "Miner", "Dighunter", "Veinmaster", "Weaponsmith",
            "MasterSmith", "LegendaryForgemaster"
    );

    public UseBooster(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onBoosterRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item != null && item.getType() == Material.DRAGON_BREATH && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                String boosterUsed = getBoosterUsed(item);
                if (boosterUsed != null) {

                    // Remove the item from the player's hand
                    if (plugin.boosterIs == null) {
                        if (item.getAmount() >= 1) {
                            item.setAmount(item.getAmount() - 1);
                            String getBoosterType = boosterUsed;
                            if (boosterUsed.contains("Personal")){
                                if (getBoosterType.contains("EXP")) {
                                    String getBoosterMinute = boosterUsed.replaceAll("EXP ", "");
                                    String getBooster = getBoosterMinute.replaceAll("Minute", "").replaceAll("Personal ", "");

                                    String[] numbers = getBooster.split(" ");

                                    int expBoosterInt = Integer.parseInt(numbers[0]);
                                    int minuteBooster = Integer.parseInt(numbers[1]);

                                    double expBooster = expBoosterInt / 100.0;

                                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                                        onlinePlayer.playSound(onlinePlayer, Sound.ENTITY_ENDER_DRAGON_GROWL, 100, 0f);
                                    }
                                    executeCommand("lp user " + player.getName() + " permission settemp jobs.boost.all.exp." + expBooster + " true 60m");

                                    Bukkit.broadcastMessage("");
                                    Bukkit.broadcastMessage("§2§lJobs Booster » §e" + player.getName() + " §ftelah mengaktifkan §bPersonal " + expBoosterInt + "% Jobs EXP Booster§f! selama §d"+minuteBooster+" menit§f!");
                                    Bukkit.broadcastMessage("");

                                } else if (getBoosterType.contains("Money")) {
                                    String getBoosterMinute = boosterUsed.replaceAll("Money ", "");
                                    String getBooster = getBoosterMinute.replaceAll("Minute", "").replaceAll("Personal ", "");
                                    String[] numbers = getBooster.split(" ");

                                    int moneyBoosterInt = Integer.parseInt(numbers[0]);
                                    int minuteBooster = Integer.parseInt(numbers[1]);

                                    double expBooster = moneyBoosterInt / 100.0;

                                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                                        onlinePlayer.playSound(onlinePlayer, Sound.ENTITY_ENDER_DRAGON_GROWL, 100, 0f);
                                    }
                                    executeCommand("lp user " + player.getName() + " permission settemp jobs.boost.all.money." + expBooster + " true 60m");

                                    Bukkit.broadcastMessage("");
                                    Bukkit.broadcastMessage("§2§lJobs Booster » §e" + player.getName() + " §ftelah mengaktifkan §bPersonal " + moneyBoosterInt + "% Jobs Money Booster§f! selama §d"+minuteBooster+" menit§f!");
                                    Bukkit.broadcastMessage("");
                                }
                                else if (getBoosterType.contains("Both")) {
                                    String getBoosterMinute = boosterUsed.replaceAll("Both ", "");
                                    String getBooster = getBoosterMinute.replaceAll("Minute", "").replaceAll("Personal ", "");
                                    String[] numbers = getBooster.split(" ");

                                    int moneyBoosterInt = Integer.parseInt(numbers[0]);
                                    int minuteBooster = Integer.parseInt(numbers[1]);

                                    double expBooster = moneyBoosterInt / 100.0;

                                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                                        onlinePlayer.playSound(onlinePlayer, Sound.ENTITY_ENDER_DRAGON_GROWL, 100, 0f);
                                    }
                                    executeCommand("lp user " + player.getName() + " permission settemp jobs.boost.all.money." + expBooster + " true 60m");
                                    executeCommand("lp user " + player.getName() + " permission settemp jobs.boost.all.exp." + expBooster + " true 60m");

                                    Bukkit.broadcastMessage("");
                                    Bukkit.broadcastMessage("§2§lJobs Booster » §e" + player.getName() + " §ftelah mengaktifkan §bPersonal " + moneyBoosterInt + "% Jobs Both Booster§f! selama §d"+minuteBooster+" menit§f!");
                                    Bukkit.broadcastMessage("");

                                }

                            }
                            else {
                                if (getBoosterType.contains("EXP")) {
                                    String getBoosterMinute = boosterUsed.replaceAll("EXP ", "");
                                    String getBooster = getBoosterMinute.replaceAll("Minute", "");

                                    String[] numbers = getBooster.split(" ");

                                    int expBoosterInt = Integer.parseInt(numbers[0]);
                                    int minuteBooster = Integer.parseInt(numbers[1]);

                                    double expBooster = expBoosterInt / 100.0;

                                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                                        onlinePlayer.playSound(onlinePlayer, Sound.ENTITY_ENDER_DRAGON_GROWL, 100, 0f);
                                    }
                                    for (String job : jobsArray) {
                                        executeCommand("jobs boost " + job + " exp " + minuteBooster + "m " + expBooster);
                                    }
                                    scheduleBoosterBroadcast(minuteBooster, player.getName(), "EXP", expBoosterInt, minuteBooster);
                                } else if (getBoosterType.contains("Money")) {
                                    String getBoosterMinute = boosterUsed.replaceAll("Money ", "");
                                    String getBooster = getBoosterMinute.replaceAll("Minute", "");
                                    String[] numbers = getBooster.split(" ");

                                    int moneyBoosterInt = Integer.parseInt(numbers[0]);
                                    int minuteBooster = Integer.parseInt(numbers[1]);

                                    double expBooster = moneyBoosterInt / 100.0;

                                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                                        onlinePlayer.playSound(onlinePlayer, Sound.ENTITY_ENDER_DRAGON_GROWL, 100, 0f);
                                    }
                                    for (String job : jobsArray) {
                                        executeCommand("jobs boost " + job + " money " + minuteBooster + "m " + expBooster);
                                    }
                                    scheduleBoosterBroadcast(minuteBooster, player.getName(), "Money", moneyBoosterInt, minuteBooster);
                                }
                                else if (getBoosterType.contains("Both")) {
                                    String getBoosterMinute = boosterUsed.replaceAll("Both ", "");
                                    String getBooster = getBoosterMinute.replaceAll("Minute", "");
                                    String[] numbers = getBooster.split(" ");

                                    int moneyBoosterInt = Integer.parseInt(numbers[0]);
                                    int minuteBooster = Integer.parseInt(numbers[1]);

                                    double expBooster = moneyBoosterInt / 100.0;

                                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                                        onlinePlayer.playSound(onlinePlayer, Sound.ENTITY_ENDER_DRAGON_GROWL, 100, 0f);
                                    }
                                    for (String job : jobsArray) {
                                        executeCommand("jobs boost " + job + " money " + minuteBooster + "m " + expBooster);
                                        executeCommand("jobs boost " + job + " exp " + minuteBooster + "m " + expBooster);
                                    }
                                    scheduleBoosterBroadcast(minuteBooster, player.getName(), "Money and Exp", moneyBoosterInt, minuteBooster);
                                }
                            }
                        }
                    }else{
                        event.getPlayer().sendMessage("§cGagal mengaktifkan booster, sedang ada booster yang aktif!");
                    }
                } else {
                    event.getPlayer().sendMessage("Not a valid booster item.");
                }
            }
        }
    }
    private void executeCommand(String command) {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Bukkit.dispatchCommand(console, command);
    }
    public static String getBoosterUsed(ItemStack item) {
        if (item == null || item.getType() != Material.DRAGON_BREATH || !item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) {
            return null; // Not a valid booster item
        }

        String displayName = item.getItemMeta().getDisplayName();

        // Check if the item name contains "EXP" or "Money"
        if (displayName.contains("EXP")) {
            String percentage = getSubstringBetween(displayName, "§d", "%");
            String duration = getSubstringBetween(displayName, "§b", "Minute");
            return percentage + " EXP " + duration + "Minute";
        } else if (displayName.contains("Money")) {
            String percentage = getSubstringBetween(displayName, "§d", "%");
            String duration = getSubstringBetween(displayName, "§b", "Minute");
            return percentage + " Money " + duration + "Minute";
        }
        else if (displayName.contains("Both")) {
            String percentage = getSubstringBetween(displayName, "§d", "%");
            String duration = getSubstringBetween(displayName, "§b", "Minute");
            return percentage + " Both " + duration + "Minute";
        }

        return null; // Unknown booster type
    }

    private static String getSubstringBetween(String text, String start, String end) {
        int startIndex = text.indexOf(start);
        if (startIndex != -1) {
            int endIndex = text.indexOf(end, startIndex + start.length());
            if (endIndex != -1) {
                return text.substring(startIndex + start.length(), endIndex);
            }
        }
        return null;
    }
    private void scheduleBoosterBroadcast(int durationMinutes, String playerName, String boosterType, int boosterValue, int minuteBooster) {
        final int[] remainingMinutes = {durationMinutes};
        new BukkitRunnable() {

            @Override
            public void run() {
                if (remainingMinutes[0] > 0) {
                    remainingMinutes[0]--;
                    plugin.boosterIs = playerName;
                } else {
                    broadcastBoosterOff();
                    plugin.boosterIs = null;
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 1200); // 1200 ticks = 1 minute
        new BukkitRunnable() {

            @Override
            public void run() {
                if (remainingMinutes[0] > 0) {
                    broadcastBoosterActivation(playerName, boosterType, boosterValue, remainingMinutes[0]+1);
                } else {
                    broadcastBoosterOff();
                    plugin.boosterIs = null;
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 6000); // 1200 ticks = 1 minute
    }

    private void broadcastBoosterActivation(String playerName, String boosterType, int boosterValue, int remainingMinutes) {
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("§2§lJobs Booster » §e" + playerName + " §ftelah mengaktifkan §b" + boosterValue + "% Jobs " + boosterType + " Booster§f! Tersisa §d" + remainingMinutes + " Menit§f!");
        Bukkit.broadcastMessage("§fGunakan command §e/terimakasih §funtuk mengapresiasi §e" + playerName + "§f!");
        Bukkit.broadcastMessage("");

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.playSound(onlinePlayer, Sound.ENTITY_WITHER_AMBIENT, 100, 0f);
        }
    }

    private void broadcastBoosterOff() {
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("§2§lJobs Booster » §cTelah habis!");
        Bukkit.broadcastMessage("");

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.playSound(onlinePlayer, Sound.ENTITY_WITHER_SHOOT, 100, 0f);
        }
    }

}
