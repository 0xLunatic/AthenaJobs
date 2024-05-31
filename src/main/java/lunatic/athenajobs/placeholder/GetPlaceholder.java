package lunatic.athenajobs.placeholder;

import com.google.common.base.Joiner;
import lunatic.athenajobs.Main;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GetPlaceholder extends PlaceholderExpansion {
    private Main plugin;
    private String[] randomStrings = {"BebanHidup", "CalonAlmarhum", "SeringPatahHati", "SeringSakitHati",
            "MerasaHampa", "Kosong", "LemahLetihLesu", "SeringTiduran", "Pemalas" ,"PengangguranBerpotensi","Gelandangan",
            "Pengangguran", "Beban", "PencariKerja", "Tunawisma", "CalonPekerja", "Freshgraduate",
            "HanyaDuduk", "Bersantai", "MalasBergerak", "LelahBelajar", "SeorangPemimpi", "CalonPresiden",
            "MatiRasa", "HubunganRetak", "RomansaHancur", "SeringTerlukaBatin", "SeringTerdiamSendiri",
            "SeringTerbuangJauh", "Jomblo", "PatahHati", "Backburner", "JadiSecondChoice", "JadiBadut", "Ngebadut"};
    private String shuffledJobsName;

    public GetPlaceholder(Main plugin) {
        this.plugin = plugin;

        new BukkitRunnable() {
            @Override
            public void run() {
                shuffleRandomStrings();
            }
        }.runTaskTimer(plugin, 0, 20 * 60 * 2);
    }

    public String getJobsName(Player player) {
        String result = PlaceholderAPI.setPlaceholders(player, "%jobsr_user_jobs%");
        String jobsName = ChatColor.stripColor(result);
        if (!jobsName.equalsIgnoreCase("None")) {
            return jobsName;
        } else {
            // Use the shuffledJobsName if available, otherwise generate a new one
            return shuffledJobsName != null ? shuffledJobsName : generateNewShuffledJobsName();
        }
    }

    private void shuffleRandomStrings() {
        List<String> list = Arrays.asList(randomStrings);
        Collections.shuffle(list);
        shuffledJobsName = list.get(0); // Store the shuffled value
    }

    private String generateNewShuffledJobsName() {
        Random random = new Random();
        int index = random.nextInt(randomStrings.length);
        return randomStrings[index];
    }

    public String getTotalLevel(Player player) {
        return PlaceholderAPI.setPlaceholders(player, "%jobsr_user_totallevels%");
    }

    public String getEXPBooster(Player player) {
        String rawValue = PlaceholderAPI.setPlaceholders(player, "%jobsr_user_boost_" + ChatColor.stripColor(getJobsName(player)) + "_exp%");

        double boostValue = Double.parseDouble(rawValue);

        int percentageValue = (int) (boostValue * 100);

        return String.format("%d%%", percentageValue);
    }

    public String getMoneyBooster(Player player) {
        String rawValue = PlaceholderAPI.setPlaceholders(player, "%jobsr_user_boost_" + ChatColor.stripColor(getJobsName(player)) + "_money%");

        double boostValue = Double.parseDouble(rawValue);

        int percentageValue = (int) (boostValue * 100);

        return String.format("%d%%", percentageValue);
    }

    @Override
    public @NotNull String getIdentifier() {
        return "AthenaJobs";
    }

    @Override
    public @NotNull String getAuthor() {
        return Joiner.on(", ").join(this.plugin.getDescription().getAuthors());
    }

    @Override
    public @NotNull String getVersion() {
        return this.plugin.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    private String formatJobsName(String jobsName) {
        String[] splitJobsName = jobsName.split("(?=[A-Z])");

        if (splitJobsName.length >= 2) {
            StringBuilder formattedJobsName = new StringBuilder();

            // Exclude the first uppercase letter from adding a space
            formattedJobsName.append(splitJobsName[0]);

            for (int i = 1; i < splitJobsName.length; i++) {
                formattedJobsName.append(" ").append(splitJobsName[i]);
            }

            return formattedJobsName.toString();
        }

        return jobsName;
    }
    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if (params.equalsIgnoreCase("jobsName")) {
            String jobsName = getJobsName(player.getPlayer());
            String formattedJobsName = formatJobsName(jobsName);

            return "§fJobs : §a" + formattedJobsName + " §7(§e" + getTotalLevel(player.getPlayer()) + "§7)";
        }
        if (params.equalsIgnoreCase("expJobsBooster")) {
            String jobsName = ChatColor.stripColor(getJobsName(player.getPlayer()));
            if (!containsAnySubstring(jobsName, randomStrings)) {
                String expJobsBooster = getEXPBooster((Player) player);
                return "§5EXP Boost §f: " + expJobsBooster;
            } else {
                return "";
            }
        }
        if (params.equalsIgnoreCase("moneyJobsBooster")) {
            String jobsName = ChatColor.stripColor(getJobsName(player.getPlayer()));
            if (!containsAnySubstring(jobsName, randomStrings)) {
                String moneyJobsBooster = getMoneyBooster((Player) player);
                return "§6Money Boost §f: " + moneyJobsBooster;
            } else {
                return "";
            }
        }

        return null;
    }
    private boolean containsAnySubstring(String input, String[] substrings) {
        for (String substring : substrings) {
            if (input.contains(substring)) {
                return true;
            }
        }
        return false;
    }
}
