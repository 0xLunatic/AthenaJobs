package lunatic.athenajobs;

import lunatic.athenajobs.commands.GiveBooster;
import lunatic.athenajobs.commands.JobsSelectorCommand;
import lunatic.athenajobs.commands.Terimakasih;
import lunatic.athenajobs.event.BlockPlacerPrevent;
import lunatic.athenajobs.event.OnJobsJoin;
import lunatic.athenajobs.event.UseBooster;
import lunatic.athenajobs.placeholder.GetPlaceholder;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public Economy economy;
    public String boosterIs;

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Athena Jobs Enabled!");

        getServer().getPluginManager().registerEvents(new OnJobsJoin(this), this);
        getServer().getPluginManager().registerEvents(new UseBooster(this), this);
        getServer().getPluginManager().registerEvents(new BlockPlacerPrevent(this), this);
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new GetPlaceholder(this).register();
        }

        getCommand("helpjobs").setExecutor(new JobsSelectorCommand(this));
        getCommand("givebooster").setExecutor(new GiveBooster(this));
        getCommand("terimakasih").setExecutor(new Terimakasih(this));

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp != null) {
            economy = rsp.getProvider();
        } else {
            throw new RuntimeException("Vault or an Economy plugin is not installed!");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
