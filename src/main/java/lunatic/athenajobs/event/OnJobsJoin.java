package lunatic.athenajobs.event;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.api.JobsJoinEvent;
import com.gamingmesh.jobs.api.JobsLeaveEvent;
import com.gamingmesh.jobs.container.Job;
import com.gamingmesh.jobs.container.JobsPlayer;
import com.gamingmesh.jobs.dao.JobsDAO;
import com.gamingmesh.jobs.dao.JobsDAOData;
import lunatic.athenajobs.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnJobsJoin implements Listener {
    private Main plugin;

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
                event.getPlayer().joinJob(Jobs.getJob("Worker"));
                Jobs.getJobsDAO().cleanJobs();
                Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
            }
        }else{
            if (event.getPlayer().getTotalLevels() == 0) {
                player.sendMessage("§aSuccessfuly joined as Worker.");
                event.getPlayer().joinJob(Jobs.getJob("Worker"));
                Jobs.getJobsDAO().cleanJobs();
                Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
            }
        }
        // Join First Starter Jobs Move
        if (event.getJob().getName().equalsIgnoreCase("Brewer")) {
            if (isInJobs(player, "Worker")) {
                if (isEnoughLevel(player, 25)) {
                    event.getPlayer().leaveJob(Jobs.getJob("Worker"));
                    event.getPlayer().joinJob(Jobs.getJob("Brewer"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f♨");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Worker"));
                    event.getPlayer().joinJob(Jobs.getJob("Crafter"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f♯");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Worker"));
                    event.getPlayer().joinJob(Jobs.getJob("Digger"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f♦");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Worker"));
                    event.getPlayer().joinJob(Jobs.getJob("Enchanter"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f∮");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Worker"));
                    event.getPlayer().joinJob(Jobs.getJob("Farmer"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f♆");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Worker"));
                    event.getPlayer().joinJob(Jobs.getJob("Fisherman"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f✥");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Worker"));
                    event.getPlayer().joinJob(Jobs.getJob("Hunter"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f☣");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Worker"));
                    event.getPlayer().joinJob(Jobs.getJob("Miner"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&fϡ");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Worker"));
                    event.getPlayer().joinJob(Jobs.getJob("Weaponsmith"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f⍫");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Worker"));
                    event.getPlayer().joinJob(Jobs.getJob("Lumberjack"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f≠");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Worker"));
                    event.getPlayer().joinJob(Jobs.getJob("Killer"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f☠");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Brewer"));
                    event.getPlayer().joinJob(Jobs.getJob("MasterBrewer"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&e♨");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Crafter"));
                    event.getPlayer().joinJob(Jobs.getJob("Artisan"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&e♯");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Digger"));
                    event.getPlayer().joinJob(Jobs.getJob("Excavator"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&e♦");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Enchanter"));
                    event.getPlayer().joinJob(Jobs.getJob("Glyphweaver"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&e∮");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Farmer"));
                    event.getPlayer().joinJob(Jobs.getJob("Agriculturist"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&e♆");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Fisherman"));
                    event.getPlayer().joinJob(Jobs.getJob("MasterAngler"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&e✥");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Hunter"));
                    event.getPlayer().joinJob(Jobs.getJob("Scout"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&e☣");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Miner"));
                    event.getPlayer().joinJob(Jobs.getJob("Dighunter"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&eϡ");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Weaponsmith"));
                    event.getPlayer().joinJob(Jobs.getJob("MasterSmith"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&e⍫");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Lumberjack"));
                    event.getPlayer().joinJob(Jobs.getJob("Timberer"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&e≠");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Killer"));
                    event.getPlayer().joinJob(Jobs.getJob("Villain"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&e☠");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("MasterBrewer"));
                    event.getPlayer().joinJob(Jobs.getJob("AlchemicalDistiller"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&c♨");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Artisan"));
                    event.getPlayer().joinJob(Jobs.getJob("MasterArtisan"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&c♯");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Excavator"));
                    event.getPlayer().joinJob(Jobs.getJob("Earthshaper"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&c♦");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Glyphweaver"));
                    event.getPlayer().joinJob(Jobs.getJob("ArcaneArtisan"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&c∮");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Agriculturist"));
                    event.getPlayer().joinJob(Jobs.getJob("Harvestmaster"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&c♆");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("MasterAngler"));
                    event.getPlayer().joinJob(Jobs.getJob("AquaticMaestro"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&c✥");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Scout"));
                    event.getPlayer().joinJob(Jobs.getJob("Ranger"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&c☣");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Dighunter"));
                    event.getPlayer().joinJob(Jobs.getJob("Veinmaster"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&cϡ");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("MasterSmith"));
                    event.getPlayer().joinJob(Jobs.getJob("LegendaryForgemaster"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&c⍫");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Timberer"));
                    event.getPlayer().joinJob(Jobs.getJob("Arborist"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&c≠");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
                    event.getPlayer().leaveJob(Jobs.getJob("Villain"));
                    event.getPlayer().joinJob(Jobs.getJob("Psychopath"));
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&c☠");
                    Jobs.getJobsDAO().cleanJobs();
                    Jobs.getJobsDAO().joinJob(event.getPlayer(), event.getPlayer().getJobProgression(event.getJob()));
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
        if (Jobs.getPlayerManager().getJobsPlayer(player).isInJob(Jobs.getJob(jobs))){
            return true;
        }
        return false;
    }
    public boolean isEnoughLevel(Player player, int level){
        if (Jobs.getPlayerManager().getJobsPlayer(player).getTotalLevels() >= level){
            return true;
        }
        return false;
    }
    private void setSuffix(Player player, String suffix) {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        String command = "lp user " + player.getName() + " meta setsuffix 0 \"" + suffix + "\"";
        Bukkit.getServer().dispatchCommand(console, command);
    }
    @EventHandler
    public void jobsLeaveEvent(JobsLeaveEvent event){
        clearSuffix(event.getPlayer().getPlayer());
    }
    private void clearSuffix(Player player){
        ConsoleCommandSender console1 = Bukkit.getServer().getConsoleSender();
        String command1 = "lp user " + player.getName() + " meta clear";
        Bukkit.getServer().dispatchCommand(console1, command1);
    }
}
