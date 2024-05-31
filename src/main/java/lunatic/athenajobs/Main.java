package lunatic.athenajobs;

import lunatic.athenajobs.commands.AthenaJobsCommand;
import lunatic.athenajobs.commands.GiveBooster;
import lunatic.athenajobs.commands.JobsSelectorCommand;
import lunatic.athenajobs.commands.Terimakasih;
import lunatic.athenajobs.commands.prestige.PrestigeCommand;
import lunatic.athenajobs.commands.prestige.db.PrestigeDBConnection;
import lunatic.athenajobs.data.FileManager;
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
    public PrestigeDBConnection dbConnection;
    public FileManager config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        config = new FileManager(this);
        saveDefaultConfig();
        config.getConfig("shopGui.yml").saveDefaultConfig();

        // Initialize database connection
        dbConnection = new PrestigeDBConnection(this);
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
        getCommand("prestige").setExecutor(new PrestigeCommand(this));
        getCommand("athenajobs").setExecutor(new AthenaJobsCommand(this));

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
        if (dbConnection != null) {
            dbConnection.closeConnection();
        }
    }
    public PrestigeDBConnection getDbConnection() {
        return dbConnection;
    }
}
