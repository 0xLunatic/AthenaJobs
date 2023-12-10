package lunatic.athenajobs.event;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.api.JobsJoinEvent;
import com.gamingmesh.jobs.container.JobProgression;
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f♨");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Worker"), 5000);
                    event.getPlayer().transferJob(Jobs.getJob("Worker"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Worker"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f♯");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Worker"), 5000);
                    event.getPlayer().transferJob(Jobs.getJob("Worker"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Worker"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f♦");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Worker"), 5000);
                    event.getPlayer().transferJob(Jobs.getJob("Worker"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Worker"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f∮");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Worker"), 5000);
                    event.getPlayer().transferJob(Jobs.getJob("Worker"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Worker"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f♆");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Worker"), 5000);
                    event.getPlayer().transferJob(Jobs.getJob("Worker"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Worker"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f✥");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Worker"), 5000);
                    event.getPlayer().transferJob(Jobs.getJob("Worker"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Worker"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f☣");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Worker"), 5000);
                    event.getPlayer().transferJob(Jobs.getJob("Worker"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Worker"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&fϡ");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Worker"), 5000);
                    event.getPlayer().transferJob(Jobs.getJob("Worker"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Worker"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f⍫");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Worker"), 5000);
                    event.getPlayer().transferJob(Jobs.getJob("Worker"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Worker"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f≠");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Worker"), 5000);
                    event.getPlayer().transferJob(Jobs.getJob("Worker"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Worker"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&f☠");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Worker"), 5000);
                    event.getPlayer().transferJob(Jobs.getJob("Worker"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Worker"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&e♨");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Brewer"), 20000);
                    event.getPlayer().transferJob(Jobs.getJob("Brewer"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Brewer"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&e♯");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Crafter"), 20000);
                    event.getPlayer().transferJob(Jobs.getJob("Crafter"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Crafter"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&e♦");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Digger"), 20000);
                    event.getPlayer().transferJob(Jobs.getJob("Digger"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Digger"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&e∮");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Enchanter"), 20000);
                    event.getPlayer().transferJob(Jobs.getJob("Enchanter"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Enchanter"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&e♆");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Farmer"), 20000);
                    event.getPlayer().transferJob(Jobs.getJob("Farmer"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Farmer"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&e✥");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Fisherman"), 20000);
                    event.getPlayer().transferJob(Jobs.getJob("Fisherman"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Fisherman"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&e☣");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Hunter"), 20000);
                    event.getPlayer().transferJob(Jobs.getJob("Hunter"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Hunter"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&eϡ");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Miner"), 20000);
                    event.getPlayer().transferJob(Jobs.getJob("Miner"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Miner"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&e⍫");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Weaponsmith"), 20000);
                    event.getPlayer().transferJob(Jobs.getJob("Weaponsmith"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Weaponsmith"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&e≠");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Lumberjack"), 20000);
                    event.getPlayer().transferJob(Jobs.getJob("Lumberjack"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Lumberjack"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&e☠");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Killer"), 20000);
                    event.getPlayer().transferJob(Jobs.getJob("Killer"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Killer"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&c♨");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("MasterBrewer"), 100000);
                    event.getPlayer().transferJob(Jobs.getJob("MasterBrewer"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("MasterBrewer"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&c♯");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Artisan"), 100000);
                    event.getPlayer().transferJob(Jobs.getJob("Artisan"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Artisan"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&c♦");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Excavator"), 100000);
                    event.getPlayer().transferJob(Jobs.getJob("Excavator"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Excavator"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&c∮");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Glyphweaver"), 100000);
                    event.getPlayer().transferJob(Jobs.getJob("Glyphweaver"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Glyphweaver"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&c♆");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Agriculturist"), 100000);
                    event.getPlayer().transferJob(Jobs.getJob("Agriculturist"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Agriculturist"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&c✥");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("MasterAngler"), 100000);
                    event.getPlayer().transferJob(Jobs.getJob("MasterAngler"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("MasterAngler"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&c☣");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Scout"), 100000);
                    event.getPlayer().transferJob(Jobs.getJob("Scout"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Scout"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&cϡ");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Dighunter"), 100000);
                    event.getPlayer().transferJob(Jobs.getJob("Dighunter"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Dighunter"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&c⍫");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("MasterSmith"), 100000);
                    event.getPlayer().transferJob(Jobs.getJob("MasterSmith"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("MasterSmith"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&c≠");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Timberer"), 100000);
                    event.getPlayer().transferJob(Jobs.getJob("Timberer"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Timberer"));
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
                    Bukkit.broadcastMessage("\n§e§lAthenaJobs » §d" + player.getName() + " §fbaru saja berpindah Jobs ke §c" + event.getJob().getName() + "§f!\n");
                    playSoundToAll(Sound.UI_TOAST_CHALLENGE_COMPLETE, 50, 0);
                    setSuffix(player, "&c☠");
                    // Remove Exp Of Max Level Job Before Transfer, Otherwise The Exp Will Affect The Level Of New Job
                    Jobs.getPlayerManager().removeExperience(event.getPlayer(), Jobs.getJob("Villain"), 100000);
                    event.getPlayer().transferJob(Jobs.getJob("Villain"), event.getJob());
                    // Reset Level And Exp
                    event.getPlayer().getJobProgression(event.getJob()).setLevel(1);
                    event.getPlayer().getJobProgression(event.getJob()).setExperience(0);
                    // Save Player Job Into DB (Remains Unsaved When This Not Called)
                    Jobs.getJobsDAO().quitJob(event.getPlayer(), Jobs.getJob("Villain"));
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
