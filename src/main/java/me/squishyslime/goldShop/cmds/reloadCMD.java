package me.squishyslime.goldShop.cmds;

import me.squishyslime.goldShop.Main;
import me.squishyslime.goldShop.utils.EconomyManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class reloadCMD implements CommandExecutor, TabCompleter {
    private final Main plugin;
    private final EconomyManager em;
    public reloadCMD(Main plugin) {
        this.plugin = plugin;
        this.em = plugin.em;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length != 1) {
            sender.sendMessage(Component.text(String.format("[%s] Usage: /gs <reload/info>",em.prefix), TextColor.fromHexString("#db3021")));
            return false;
        }
        if(args[0].equalsIgnoreCase("reload")) {
            em.reloadConfig();
            sender.sendMessage(Component.text(String.format("[%s] Config reloaded!",em.prefix),TextColor.fromHexString("#dba600")));
        }
        else if(args[0].equalsIgnoreCase("info")) {
            sender.sendMessage(Component.text(String.format("------[%s]------",em.prefix),TextColor.fromHexString("#dba600")));
            sender.sendMessage(Component.text("Name: GoldShop",TextColor.fromHexString("#dba600")));
            sender.sendMessage(Component.text("Author: SquishySlime",TextColor.fromHexString("#dba600")));
            sender.sendMessage(Component.text("Version: " +plugin.version,TextColor.fromHexString("#dba600")));
        }
        else {
            sender.sendMessage(Component.text(String.format("[%s] Usage: /gs <reload/info>",em.prefix), TextColor.fromHexString("#db3021")));
            return false;
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(strings.length == 1) {
            return List.of("reload","info");
        }
        return null;
    }
}
