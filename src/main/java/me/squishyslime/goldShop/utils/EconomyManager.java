package me.squishyslime.goldShop.utils;

import me.squishyslime.goldShop.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.UUID;

public class EconomyManager {
    private final FileConfiguration config;
    private final Main plugin;
    public String currencySymbol;
    public boolean allowNegatives;
    public int startingMoney;
    public boolean showMoneyOnTab;
    public String prefix;
    public EconomyManager(Main plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        this.currencySymbol = config.getString("settings.currencySymbol");
        this.allowNegatives = config.getBoolean("settings.allowNegatives");
        this.startingMoney = config.getInt("settings.startingMoney");
        this.showMoneyOnTab = config.getBoolean("settings.showMoneyOnTab");
        this.prefix = config.getString("prefix");
    }
    public void reloadConfig() {
        plugin.reloadConfig();
        this.currencySymbol = config.getString("settings.currencySymbol");
        this.allowNegatives = config.getBoolean("settings.allowNegatives");
        this.startingMoney = config.getInt("settings.startingMoney");
        this.showMoneyOnTab = config.getBoolean("settings.showMoneyOnTab");
        this.prefix = config.getString("prefix");
    }

    public boolean isPlayerInConfig(UUID player) {
        return config.contains("balances." + player);
    }
    public int getMoney(UUID player) {
        return config.getInt("balances." + player,0);
    }
    public void setMoney(UUID player, int value) {
        config.set("balances." + player,value);
        plugin.saveConfig();
    }
    public void addMoney(UUID player, int value) {
        config.set("balances." + player, getMoney(player) + value);
        plugin.saveConfig();
    }
    public void removeMoney(UUID player, int value) {
        config.set("balances." + player,getMoney(player) + value);
        plugin.saveConfig();
    }

}
