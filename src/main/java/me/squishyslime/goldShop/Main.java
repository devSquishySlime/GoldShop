package me.squishyslime.goldShop;

import me.squishyslime.goldShop.cmds.balanceCMD;
import me.squishyslime.goldShop.cmds.moneyCMD;
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
        Objects.requireNonNull(getCommand("money")).setExecutor(new moneyCMD(this));
        Objects.requireNonNull(getCommand("bal")).setExecutor(new balanceCMD(this));
    }

    @Override
    public void onDisable() {
        this.saveConfig();
    }
}
