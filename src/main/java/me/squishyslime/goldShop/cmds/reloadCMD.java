package me.squishyslime.goldShop.cmds;

import me.squishyslime.goldShop.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class reloadCMD implements CommandExecutor {
    public reloadCMD(Main plugin) {
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return false;
    }
}
