package lunatic.athenajobs;

import lunatic.athenajobs.commands.GiveBooster;
import lunatic.athenajobs.commands.JobsSelectorCommand;
import lunatic.athenajobs.event.OnJobsJoin;
import lunatic.athenajobs.event.UseBooster;
import lunatic.athenajobs.placeholder.GetPlaceholder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Athena Jobs Enabled!");

        getServer().getPluginManager().registerEvents(new OnJobsJoin(this), this);
        getServer().getPluginManager().registerEvents(new UseBooster(this), this);
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new GetPlaceholder(this).register();
        }

        getCommand("helpjobs").setExecutor(new JobsSelectorCommand(this));
        getCommand("givebooster").setExecutor(new GiveBooster(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
