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
                    if (item.getAmount() >= 1) {
                        item.setAmount(item.getAmount() - 1);
                        String getBoosterType = boosterUsed;
                        if (getBoosterType.contains("EXP")){
                            String getBoosterMinute = boosterUsed.replaceAll("EXP ", "");
                            String getBooster = getBoosterMinute.replaceAll("Minute", "");

                            String[] numbers = getBooster.split(" ");

                            int expBoosterInt = Integer.parseInt(numbers[0]);
                            int minuteBooster = Integer.parseInt(numbers[1]);

                            double expBooster = expBoosterInt / 100.0;

                            Bukkit.broadcastMessage("");
                            Bukkit.broadcastMessage(" §e"+player.getName()+" §fbaru saja mengaktifkan §b" +expBooster+ "% Jobs EXP Booster §fselama §e" +minuteBooster+ " Menit §f! Seluruh player akan mendapatkan efek §eBooster §fini!");
                            Bukkit.broadcastMessage("");

                            for (Player onlinePlayer : Bukkit.getOnlinePlayers()){
                                onlinePlayer.playSound(onlinePlayer, Sound.ENTITY_ENDER_DRAGON_GROWL, 100, 0f);
                            }
                            for (String job : jobsArray) {
                                executeCommand("jobs boost " + job + " exp " + minuteBooster + "m " + expBooster);
                            }
                        }else if (getBoosterType.contains("Money")){
                            String getBoosterMinute = boosterUsed.replaceAll("Money ", "");
                            String getBooster = getBoosterMinute.replaceAll("Minute", "");
                            String[] numbers = getBooster.split(" ");

                            int expBoosterInt = Integer.parseInt(numbers[0]);
                            int minuteBooster = Integer.parseInt(numbers[1]);

                            double expBooster = expBoosterInt / 100.0;

                            Bukkit.broadcastMessage("");
                            Bukkit.broadcastMessage(" §e"+player.getName()+" §fbaru saja mengaktifkan §b" +expBooster+ "% Jobs Money Booster §fselama §e" +minuteBooster+ " Menit§f! Seluruh player akan mendapatkan efek §eBooster §fini!");
                            Bukkit.broadcastMessage("");

                            for (Player onlinePlayer : Bukkit.getOnlinePlayers()){
                                onlinePlayer.playSound(onlinePlayer, Sound.ENTITY_ENDER_DRAGON_GROWL, 100, 0f);
                            }
                            for (String job : jobsArray) {
                                executeCommand("jobs boost " + job + " money " + minuteBooster + "m " + expBooster);
                            }
                        }
                    } else {
                        player.getInventory().setItemInMainHand(null);
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

}
