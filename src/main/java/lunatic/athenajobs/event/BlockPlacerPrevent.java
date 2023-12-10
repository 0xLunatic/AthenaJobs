package lunatic.athenajobs.event;

import com.gamingmesh.jobs.api.JobsExpGainEvent;
import com.gamingmesh.jobs.api.JobsPaymentEvent;
import lunatic.athenajobs.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class BlockPlacerPrevent implements Listener {
    private Main plugin;

    public BlockPlacerPrevent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJobsExpGain(JobsExpGainEvent event) {
        Player player = event.getPlayer().getPlayer();

        // Check if the player is breaking a block nearby a dispenser
        if (isPlayerBreakingNearbyDispenser(player)) {
            // Do something when a player breaks a block in the specified area
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onJobsMoneyGain(JobsPaymentEvent event){
        Player player = event.getPlayer().getPlayer();

        // Check if the player is breaking a block nearby a dispenser
        if (isPlayerBreakingNearbyDispenser(player)) {
            // Do something when a player breaks a block in the specified area
            event.setCancelled(true);
        }
    }

    private boolean isPlayerBreakingNearbyDispenser(Player player) {
        int radius = 7; // Set your desired radius here

        // Get the player's location
        Location playerLocation = player.getLocation();

        // Iterate over the cubic area around the player
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Location checkLocation = new Location(playerLocation.getWorld(), playerLocation.getBlockX() + x, playerLocation.getBlockY() + y, playerLocation.getBlockZ() + z);
                    Block block = checkLocation.getBlock();

                    // Check if the block type is a dispenser
                    if (block.getType() == Material.DISPENSER) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
