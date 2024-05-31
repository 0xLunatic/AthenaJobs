package lunatic.athenajobs.commands.prestige.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lunatic.athenajobs.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PrestigeDBConnection {
    private final Main plugin;
    private static HikariDataSource dataSource;

    public PrestigeDBConnection(Main plugin) {
        this.plugin = plugin;
        initializeDatabaseConnection();
    }

    private void initializeDatabaseConnection() {
        FileConfiguration config = plugin.getConfig();

        String host = config.getString("database.host");
        int port = config.getInt("database.port");
        String database = config.getString("database.name");
        String user = config.getString("database.user");
        String password = config.getString("database.password");

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername(user);
        hikariConfig.setPassword(password);
        hikariConfig.setConnectionTimeout(10000);
        hikariConfig.setMaximumPoolSize(20); // Adjust the pool size as needed
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database + "?allowPublicKeyRetrieval=true&useSSL=" + "false");
        hikariConfig.setMaxLifetime(30000);
        dataSource = new HikariDataSource(hikariConfig);

        try {
            Connection connection = dataSource.getConnection();
            plugin.getLogger().info("Database connected successfully.");
            createTableIfNotExists();
            closeConnection(connection);
        } catch (SQLException e) {
            plugin.getLogger().severe("Unable to connect to the database: " + e.getMessage());
        }
    }

    private void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS prestige_coins ("
                + "player_uuid VARCHAR(36) PRIMARY KEY, "
                + "player_name VARCHAR(16) NOT NULL, "
                + "coins INT DEFAULT 0, "
                + "level INT DEFAULT 0)";
        try (PreparedStatement statement = getConnection().prepareStatement(createTableSQL)) {
            statement.executeUpdate();
            plugin.getLogger().info("Created!");
        } catch (SQLException e) {
            plugin.getLogger().severe("Failed to create the prestige_coins table: " + e.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void closeConnection(Connection connection) {
        dataSource.evictConnection(connection);
    }

    public void closeDataSource() {
        if (dataSource != null) {
            dataSource.close();
            plugin.getLogger().info("Database connection closed.");
        }
    }

    public void addPlayer(UUID playerUUID, String playerName) {
        String insertSQL = "INSERT INTO prestige_coins (player_uuid, player_name) VALUES (?, ?) ON DUPLICATE KEY UPDATE player_name = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(insertSQL)) {
            statement.setString(1, playerUUID.toString());
            statement.setString(2, playerName);
            statement.setString(3, playerName);
            statement.executeUpdate();
        } catch (SQLException e) {
            plugin.getLogger().severe("Failed to add or update player: " + e.getMessage());
        }
    }

    public int getCoins(UUID playerUUID) {
        String selectSQL = "SELECT coins FROM prestige_coins WHERE player_uuid = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(selectSQL)) {
            statement.setString(1, playerUUID.toString());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("coins");
            }
        } catch (SQLException e) {
            plugin.getLogger().severe("Failed to retrieve coins for player: " + e.getMessage());
        }
        return 0;
    }

    public void setCoins(UUID playerUUID, int coins) {
        String updateSQL = "UPDATE prestige_coins SET coins = ? WHERE player_uuid = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(updateSQL)) {
            statement.setInt(1, coins);
            statement.setString(2, playerUUID.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            plugin.getLogger().severe("Failed to update coins for player: " + e.getMessage());
        }
    }

    public void addCoins(UUID playerUUID, int coins) {
        int currentCoins = getCoins(playerUUID);
        setCoins(playerUUID, currentCoins + coins);
    }

    public void subtractCoins(UUID playerUUID, int coins) {
        int currentCoins = getCoins(playerUUID);
        setCoins(playerUUID, currentCoins - coins);
    }

    public void removePlayer(UUID playerUUID) {
        String deleteSQL = "DELETE FROM prestige_coins WHERE player_uuid = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(deleteSQL)) {
            statement.setString(1, playerUUID.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            plugin.getLogger().severe("Failed to remove player: " + e.getMessage());
        }
    }

    // New Methods for Managing Player Levels
    public int getLevel(UUID playerUUID) {
        String selectSQL = "SELECT level FROM prestige_coins WHERE player_uuid = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(selectSQL)) {
            statement.setString(1, playerUUID.toString());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("level");
            }
        } catch (SQLException e) {
            plugin.getLogger().severe("Failed to retrieve level for player: " + e.getMessage());
        }
        return 0;
    }

    public void setLevel(UUID playerUUID, int level) {
        String updateSQL = "UPDATE prestige_coins SET level = ? WHERE player_uuid = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(updateSQL)) {
            statement.setInt(1, level);
            statement.setString(2, playerUUID.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            plugin.getLogger().severe("Failed to update level for player: " + e.getMessage());
        }
    }

    public void addLevel(UUID playerUUID, int level) {
        int currentLevel = getLevel(playerUUID);
        setLevel(playerUUID, currentLevel + level);
    }

    public void subtractLevel(UUID playerUUID, int level) {
        int currentLevel = getLevel(playerUUID);
        setLevel(playerUUID, currentLevel - level);
    }
}
