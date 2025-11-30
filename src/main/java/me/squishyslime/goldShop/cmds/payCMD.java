package me.squishyslime.goldShop.cmds;

import me.squishyslime.goldShop.Main;
import me.squishyslime.goldShop.utils.EconomyManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class payCMD implements CommandExecutor, TabCompleter {
    private final EconomyManager em;
    public payCMD(Main main) {
        this.em = main.em;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length != 2) {
            sender.sendMessage(Component.text(String.format("[%s] Usage: /pay <player> <amount>",em.prefix), TextColor.fromHexString("#db3021")));
            return false;
        }
        if(!(sender instanceof Player p)) {
            sender.sendMessage(Component.text(String.format("[%s] You have to be a player to use this command!",em.prefix),TextColor.fromHexString("#db3021")));
            return false;
        }
        OfflinePlayer oP = Bukkit.getOfflinePlayer(args[0]);
        if(!oP.hasPlayedBefore() && !oP.isOnline()) {
            sender.sendMessage(Component.text(String.format("[%s] That player doesn't exist!",em.prefix),TextColor.fromHexString("#db3021")));
            return false;
        }
        try {
            int amount = Integer.parseInt(args[1]);
            if(amount < 0) {
                sender.sendMessage(Component.text(String.format("[%s] Negative numbers aren't allowed!",em.prefix),TextColor.fromHexString("#db3021")));
                return false;
            }
            em.removeMoney(p.getUniqueId(),amount);
            em.addMoney(oP.getUniqueId(),amount);
            sender.sendMessage(Component.text(String.format("[%s] Paid %d to %s",em.prefix,amount, oP.getName()),TextColor.fromHexString("#30e607")));

        } catch (NumberFormatException e) {
            sender.sendMessage(Component.text(String.format("[%s] %s is not an integer!",em.prefix,args[1]),TextColor.fromHexString("#db3021")));
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(strings.length == 1) {
            List<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
            List<String> output = new ArrayList<>();
            for (Player pO : onlinePlayers) {
                output.add(pO.getName());
            }
            return output;
        }
        else if(strings.length == 2) {
            return List.of("1","25","50","100");
        }
        return null;
    }
}
