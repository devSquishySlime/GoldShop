package me.squishyslime.goldShop;

import me.squishyslime.goldShop.cmds.reloadCMD;
import me.squishyslime.goldShop.utils.EconomyManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        EconomyManager em = new EconomyManager(this);

//        Commands
        getCommand("reload").setExecutor(new reloadCMD(this));
    }

    @Override
    public void onDisable() {
        this.saveConfig();
    }
}
