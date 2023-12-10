package lunatic.athenajobs.event;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.api.JobsJoinEvent;
import com.gamingmesh.jobs.container.JobProgression;
import lunatic.athenajobs.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnJobsJoin implements Listener {
    private final Main plugin;

    public OnJobsJoin(Main plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onJobsJoin(JobsJoinEvent event){
        event.setCancelled(true);
        Player player = event.getPlayer().getPlayer();
        if (!event.getJob().getName().equalsIgnoreCase("Worker")){
            if (event.getPlayer().getTotalLevels() == 0) {
                player.sendMessage("§cFailed to choose that Jobs! Joining as Worker.");
                player.sendMessage("§cYou don't have jobs before, you need to join Worker first!");
                // Force Player To Join Worker
                event.getPlayer().joinJob(Jobs.getJob("Worker"));
                // Save Player Job Into DB (Remains Unsaved When This Not Called)
                Jobs.getJobsDAO().joinJob(event.getPlayer(), new JobProgression(Jobs.getJob("Worker"), event.getPlayer(), 0, 0));
            }
        }else{
            if (event.getPlayer().getTotalLevels() == 0) {
                player.sendMessage("§aSuccessfuly joined as Worker.");
                // Force Player To Join Worker
                event.getPlayer().joinJob(Jobs.getJob("Worker"));
                // Save Player Job Into DB (Remains Unsaved When This Not Called)
                Jobs.getJobsDAO().joinJob(event.getPlayer(), new JobProgression(Jobs.getJob("Worker"), event.getPlayer(), 0, 0));
            }
        }
        // Join First Starter Jobs Move
        if (event.getJob().getName().equalsIgnoreCase("Brewer")) {
            if (isInJobs(player, "Worker")) {
                if (isEnoughLevel(player, 25)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Worker"), Jobs.getJob("Brewer"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f♨");
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            }else{
                player.sendMessage("§cYou must be Worker to join this job!");
            }
        }
        if (event.getJob().getName().equalsIgnoreCase("Crafter")) {
            if (isInJobs(player, "Worker")) {
                if (isEnoughLevel(player, 25)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Worker"), Jobs.getJob("Crafter"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f♯");
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            }else{
                player.sendMessage("§cYou must be Worker to join this job!");
            }
        }
        if (event.getJob().getName().equalsIgnoreCase("Digger")) {
            if (isInJobs(player, "Worker")) {
                if (isEnoughLevel(player, 25)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Worker"), Jobs.getJob("Digger"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f♦");
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            }else{
                player.sendMessage("§cYou must be Worker to join this job!");
            }
        }
        if (event.getJob().getName().equalsIgnoreCase("Enchanter")) {
            if (isInJobs(player, "Worker")) {
                if (isEnoughLevel(player, 25)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Worker"), Jobs.getJob("Enchanter"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f∮");
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            }else{
                player.sendMessage("§cYou must be Worker to join this job!");
            }
        }
        if (event.getJob().getName().equalsIgnoreCase("Farmer")) {
            if (isInJobs(player, "Worker")) {
                if (isEnoughLevel(player, 25)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Worker"), Jobs.getJob("Farmer"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f♆");
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            }else{
                player.sendMessage("§cYou must be Worker to join this job!");
            }
        }
        if (event.getJob().getName().equalsIgnoreCase("Fisherman")) {
            if (isInJobs(player, "Worker")) {
                if (isEnoughLevel(player, 25)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Worker"), Jobs.getJob("Fisherman"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f✥");
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            }else{
                player.sendMessage("§cYou must be Worker to join this job!");
            }
        }
        if (event.getJob().getName().equalsIgnoreCase("Hunter")) {
            if (isInJobs(player, "Worker")) {
                if (isEnoughLevel(player, 25)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Worker"), Jobs.getJob("Hunter"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f☣");
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            }else{
                player.sendMessage("§cYou must be Worker to join this job!");
            }
        }
        if (event.getJob().getName().equalsIgnoreCase("Miner")) {
            if (isInJobs(player, "Worker")) {
                if (isEnoughLevel(player, 25)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Worker"), Jobs.getJob("Miner"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&fϡ");
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            }else{
                player.sendMessage("§cYou must be Worker to join this job!");
            }
        }
        if (event.getJob().getName().equalsIgnoreCase("Weaponsmith")) {
            if (isInJobs(player, "Worker")) {
                if (isEnoughLevel(player, 25)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Worker"), Jobs.getJob("Weaponsmith"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f⍫");
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            }else{
                player.sendMessage("§cYou must be Worker to join this job!");
            }
        }
        if (event.getJob().getName().equalsIgnoreCase("Lumberjack")) {
            if (isInJobs(player, "Worker")) {
                if (isEnoughLevel(player, 25)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Worker"), Jobs.getJob("Lumberjack"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f≠");
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            }else{
                player.sendMessage("§cYou must be Worker to join this job!");
            }
        }
        if (event.getJob().getName().equalsIgnoreCase("Killer")) {
            if (isInJobs(player, "Worker")) {
                if (isEnoughLevel(player, 25)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Worker"), Jobs.getJob("Killer"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f☠");
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            }else{
                player.sendMessage("§cYou must be Worker to join this job!");
            }
        }
        // Join Tier 2 Jobs from Tier 1
        if (event.getJob().getName().equalsIgnoreCase("MasterBrewer")) {
            if (isInJobs(player, "Brewer")) {
                if (isEnoughLevel(player, 50)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Brewer"), Jobs.getJob("MasterBrewer"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            } else {
                player.sendMessage("§cYou only can join into the same tiered jobs!");

            }
        }
        if (event.getJob().getName().equalsIgnoreCase("Artisan")) {
            if (isInJobs(player, "Crafter")) {
                if (isEnoughLevel(player, 50)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Crafter"), Jobs.getJob("Artisan"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            } else {
                player.sendMessage("§cYou only can join into the same tiered jobs!");

            }
        }
        if (event.getJob().getName().equalsIgnoreCase("Excavator")) {
            if (isInJobs(player, "Digger")) {
                if (isEnoughLevel(player, 50)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Digger"), Jobs.getJob("Excavator"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            } else {
                player.sendMessage("§cYou only can join into the same tiered jobs!");

            }
        }
        if (event.getJob().getName().equalsIgnoreCase("Glyphweaver")) {
            if (isInJobs(player, "Enchanter")) {
                if (isEnoughLevel(player, 50)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Enchanter"), Jobs.getJob("Glyphweaver"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            } else {
                player.sendMessage("§cYou only can join into the same tiered jobs!");

            }
        }
        if (event.getJob().getName().equalsIgnoreCase("Agriculturist")) {
            if (isInJobs(player, "Farmer")) {
                if (isEnoughLevel(player, 50)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Farmer"), Jobs.getJob("Agriculturist"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            } else {
                player.sendMessage("§cYou only can join into the same tiered jobs!");

            }
        }
        if (event.getJob().getName().equalsIgnoreCase("MasterAngler")) {
            if (isInJobs(player, "Fisherman")) {
                if (isEnoughLevel(player, 50)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Fisherman"), Jobs.getJob("MasterAngler"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            } else {
                player.sendMessage("§cYou only can join into the same tiered jobs!");

            }
        }
        if (event.getJob().getName().equalsIgnoreCase("Scout")) {
            if (isInJobs(player, "Hunter")) {
                if (isEnoughLevel(player, 50)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Hunter"), Jobs.getJob("Scout"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            } else {
                player.sendMessage("§cYou only can join into the same tiered jobs!");

            }
        }
        if (event.getJob().getName().equalsIgnoreCase("Dighunter")) {
            if (isInJobs(player, "Miner")) {
                if (isEnoughLevel(player, 50)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Miner"), Jobs.getJob("Dighunter"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            } else {
                player.sendMessage("§cYou only can join into the same tiered jobs!");

            }
        }
        if (event.getJob().getName().equalsIgnoreCase("MasterSmith")) {
            if (isInJobs(player, "Weaponsmith")) {
                if (isEnoughLevel(player, 50)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Weaponsmith"), Jobs.getJob("MasterSmith"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            } else {
                player.sendMessage("§cYou only can join into the same tiered jobs!");

            }
        }
        if (event.getJob().getName().equalsIgnoreCase("Timberer")) {
            if (isInJobs(player, "Lumberjack")) {
                if (isEnoughLevel(player, 50)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Lumberjack"), Jobs.getJob("Timberer"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            } else {
                player.sendMessage("§cYou only can join into the same tiered jobs!");

            }
        }
        if (event.getJob().getName().equalsIgnoreCase("Villain")) {
            if (isInJobs(player, "Killer")) {
                if (isEnoughLevel(player, 50)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Killer"), Jobs.getJob("Villain"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            } else {
                player.sendMessage("§cYou only can join into the same tiered jobs!");

            }
        }
        // Join Tier 3 Jobs from Tier 2
        if (event.getJob().getName().equalsIgnoreCase("AlchemicalDistiller")) {
            if (isInJobs(player, "MasterBrewer")) {
                if (isEnoughLevel(player, 100)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("MasterBrewer"), Jobs.getJob("AlchemicalDistiller"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            } else {
                player.sendMessage("§cYou only can join into the same tiered jobs!");

            }
        }
        if (event.getJob().getName().equalsIgnoreCase("MasterArtisan")) {
            if (isInJobs(player, "Artisan")) {
                if (isEnoughLevel(player, 100)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Artisan"), Jobs.getJob("MasterArtisan"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            } else {
                player.sendMessage("§cYou only can join into the same tiered jobs!");

            }
        }
        if (event.getJob().getName().equalsIgnoreCase("Earthshaper")) {
            if (isInJobs(player, "Excavator")) {
                if (isEnoughLevel(player, 100)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Excavator"), Jobs.getJob("Earthshaper"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            } else {
                player.sendMessage("§cYou only can join into the same tiered jobs!");

            }
        }
        if (event.getJob().getName().equalsIgnoreCase("ArcaneArtisan")) {
            if (isInJobs(player, "Glyphweaver")) {
                if (isEnoughLevel(player, 100)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Glyphweaver"), Jobs.getJob("ArcaneArtisan"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            } else {
                player.sendMessage("§cYou only can join into the same tiered jobs!");

            }
        }
        if (event.getJob().getName().equalsIgnoreCase("Harvestmaster")) {
            if (isInJobs(player, "Agriculturist")) {
                if (isEnoughLevel(player, 100)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Agriculturist"), Jobs.getJob("Harvestmaster"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            } else {
                player.sendMessage("§cYou only can join into the same tiered jobs!");

            }
        }
        if (event.getJob().getName().equalsIgnoreCase("AquaticMaestro")) {
            if (isInJobs(player, "MasterAngler")) {
                if (isEnoughLevel(player, 100)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("MasterAngler"), Jobs.getJob("AquaticMaestro"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            } else {
                player.sendMessage("§cYou only can join into the same tiered jobs!");

            }
        }
        if (event.getJob().getName().equalsIgnoreCase("Ranger")) {
            if (isInJobs(player, "Scout")) {
                if (isEnoughLevel(player, 100)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Scout"), Jobs.getJob("Ranger"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            } else {
                player.sendMessage("§cYou only can join into the same tiered jobs!");

            }
        }
        if (event.getJob().getName().equalsIgnoreCase("Veinmaster")) {
            if (isInJobs(player, "Dighunter")) {
                if (isEnoughLevel(player, 100)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Dighunter"), Jobs.getJob("Veinmaster"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            } else {
                player.sendMessage("§cYou only can join into the same tiered jobs!");

            }
        }
        if (event.getJob().getName().equalsIgnoreCase("LegedaryForgemaster")) {
            if (isInJobs(player, "MasterSmith")) {
                if (isEnoughLevel(player, 100)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("MasterSmith"), Jobs.getJob("LegendaryForgemaster"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            } else {
                player.sendMessage("§cYou only can join into the same tiered jobs!");

            }
        }
        if (event.getJob().getName().equalsIgnoreCase("Arborist")) {
            if (isInJobs(player, "Timberer")) {
                if (isEnoughLevel(player, 100)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Timberer"), Jobs.getJob("Arborist"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            } else {
                player.sendMessage("§cYou only can join into the same tiered jobs!");

            }
        }
        if (event.getJob().getName().equalsIgnoreCase("Psychopath")) {
            if (isInJobs(player, "Villain")) {
                if (isEnoughLevel(player, 100)) {
                    Jobs.getPlayerManager().transferJob(event.getPlayer(), Jobs.getJob("Villain"), Jobs.getJob("Psychopath"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                } else {
                    player.sendMessage("§cYou don't have enough level to join this Jobs!");
                }
            } else {
                player.sendMessage("§cYou only can join into the same tiered jobs!");

            }
        }
    }
    public void playSoundToAll(Sound sound, float volume, float pitch){
        for (Player p : Bukkit.getOnlinePlayers()){
            p.playSound(p.getLocation(), sound, volume, pitch);
        }
    }
    public boolean isInJobs(Player player, String jobs){
        return Jobs.getPlayerManager().getJobsPlayer(player).isInJob(Jobs.getJob(jobs));
    }
    public boolean isEnoughLevel(Player player, int level){
        return Jobs.getPlayerManager().getJobsPlayer(player).getTotalLevels() >= level;
    }
    private void setSuffix(Player player, String suffix) {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

        String command = "lp user " + player.getName() + " meta setsuffix ' "+ suffix +"'";

        Bukkit.getServer().dispatchCommand(console, command);
    }
}
