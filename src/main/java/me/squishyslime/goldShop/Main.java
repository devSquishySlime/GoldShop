package me.squishyslime.goldShop;

import me.squishyslime.goldShop.cmds.reloadCMD;
import me.squishyslime.goldShop.utils.EconomyManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {
    public EconomyManager em;
    public String version = "1.0.0";
    @Override
    public void onEnable() {
        saveDefaultConfig();
        em = new EconomyManager(this);

//        Commands
        Objects.requireNonNull(getCommand("gs")).setExecutor(new reloadCMD(this));
    }

    @Override
    public void onDisable() {
        this.saveConfig();
    }
}
