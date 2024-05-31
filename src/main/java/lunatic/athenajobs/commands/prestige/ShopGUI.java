package lunatic.athenajobs.commands.prestige;

import lunatic.athenajobs.Main;
import lunatic.athenajobs.data.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShopGUI implements Listener {
    private final Main plugin;
    private final FileManager fileManager;

    public ShopGUI(Main plugin) {
        this.plugin = plugin;
        this.fileManager = new FileManager(plugin);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void openShop(Player player) {
        FileManager.Config config = fileManager.getConfig("shopGui.yml").reload();

        String title = ChatColor.translateAlternateColorCodes('&', config.get().getString("shop.title", "Prestige Shop"));
        int size = config.get().getInt("shop.size", 27);
        Inventory shop = Bukkit.createInventory(null, size, title);

        ConfigurationSection itemsSection = config.get().getConfigurationSection("shop.items");
        if (itemsSection != null) {
            for (String key : itemsSection.getKeys(false)) {
                ConfigurationSection itemSection = itemsSection.getConfigurationSection(key);
                if (itemSection != null) {
                    int slot = Integer.parseInt(key);
                    Material material = Material.valueOf(Objects.requireNonNull(itemSection.getString("material", "STONE")).toUpperCase());
                    String itemName = ChatColor.translateAlternateColorCodes('&', itemSection.getString("name", ""));
                    List<String> itemLore = itemSection.getStringList("lore");
                    int price = itemSection.getInt("price", 0);
                    int level = itemSection.getInt("level", 0);

                    ItemStack item;
                    ItemMeta meta;

                    if (plugin.dbConnection.getLevel(player.getUniqueId()) >= level) {
                        item = new ItemStack(material);
                        meta = item.getItemMeta();
                        if (meta != null) {
                            meta.setDisplayName(itemName);
                            if (itemLore != null && !itemLore.isEmpty()) {
                                List<String> formattedLore = new ArrayList<>();
                                for (String loreLine : itemLore) {
                                    formattedLore.add(ChatColor.translateAlternateColorCodes('&', loreLine));
                                }
                                meta.setLore(formattedLore);
                            }
                            item.setItemMeta(meta);
                        }
                    } else {
                        item = new ItemStack(Material.BARRIER);
                        meta = item.getItemMeta();
                        if (meta != null) {
                            meta.setDisplayName(ChatColor.RED + "Insufficient Level");
                            item.setItemMeta(meta);
                        }
                    }

                    shop.setItem(slot, item);
                }
            }
        }

        // Add player stats head to slot 5
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta headMeta = (SkullMeta) playerHead.getItemMeta();
        if (headMeta != null) {
            headMeta.setOwningPlayer(player);
            headMeta.setDisplayName(ChatColor.GOLD + "Prestige Stats");
            List<String> headLore = new ArrayList<>();
            headLore.add("");
            headLore.add(ChatColor.YELLOW + "Player Name: " + ChatColor.WHITE + player.getName());
            headLore.add(ChatColor.YELLOW + "Prestige Level: " + ChatColor.WHITE + plugin.dbConnection.getLevel(player.getUniqueId()));
            headLore.add(ChatColor.YELLOW + "Prestige Coins: " + ChatColor.WHITE + plugin.dbConnection.getCoins(player.getUniqueId()));
            headMeta.setLore(headLore);
            playerHead.setItemMeta(headMeta);
        }
        shop.setItem(4, playerHead); // Slot 5 in 0-indexed array is 4

        player.openInventory(shop);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!ChatColor.stripColor(event.getView().getTitle()).equalsIgnoreCase("Prestige Shop")) {
            return;
        }

        event.setCancelled(true);

        if (event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta() || event.getCurrentItem().getType().equals(Material.BARRIER) || event.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();
        ItemMeta meta = clickedItem.getItemMeta();

        if (meta == null) {
            return;
        }

        String clickedItemName = ChatColor.stripColor(meta.getDisplayName());
        Material clickedItemMaterial = clickedItem.getType();

        FileManager.Config config = fileManager.getConfig("shopGui.yml").reload();
        ConfigurationSection itemsSection = config.get().getConfigurationSection("shop.items");

        if (itemsSection == null) {
            player.sendMessage(ChatColor.RED + "Shop configuration is missing.");
            return;
        }

        for (String key : itemsSection.getKeys(false)) {
            ConfigurationSection itemSection = itemsSection.getConfigurationSection(key);
            if (itemSection != null) {
                Material material = Material.valueOf(Objects.requireNonNull(itemSection.getString("material", "STONE")).toUpperCase());
                String itemName = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', itemSection.getString("name", "")));
                int price = itemSection.getInt("price", 0);
                int level = itemSection.getInt("level", 0);
                String command = itemSection.getString("command", "");

                if (clickedItemMaterial == material && clickedItemName.equals(itemName)) {
                    if (plugin.dbConnection.getLevel(player.getUniqueId()) < level) {
                        player.sendMessage(ChatColor.RED + "You don't have enough level to buy this item!");
                        return;
                    }

                    if (plugin.dbConnection.getCoins(player.getUniqueId()) >= price) {
                        plugin.dbConnection.subtractCoins(player.getUniqueId(), price);

                        // Clone the item without price and command data
                        ItemStack boughtItem = clickedItem.clone();
                        ItemMeta boughtMeta = boughtItem.getItemMeta();
                        if (boughtMeta != null) {
                            boughtItem.setItemMeta(boughtMeta);
                        }

                        player.getInventory().addItem(boughtItem);
                        player.sendMessage(ChatColor.GREEN + "You bought the " + meta.getDisplayName() + " for " + price + " coins!");

                        if (!command.isEmpty()) {
                            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), command.replace("%player%", player.getName()));
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "You don't have enough coins to buy this item!");
                    }
                    return;
                }
            }
        }

        player.sendMessage(ChatColor.RED + "Item not found in shop configuration.");
    }
}
