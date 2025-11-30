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
import java.util.Objects;

public class moneyCMD implements CommandExecutor, TabCompleter {
    private final EconomyManager em;
    public moneyCMD(Main plugin) {
        this.em = plugin.em;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length != 3) {
            sender.sendMessage(Component.text(String.format("[%s] Usage: /money <set/take/add> <player> <amount>",em.prefix), TextColor.fromHexString("#db3021")));
            return false;
        }
        if(!args[0].equalsIgnoreCase("add") &&
                !args[0].equalsIgnoreCase("take") &&
                !args[0].equalsIgnoreCase("set")) {
            sender.sendMessage(Component.text(String.format("[%s] Usage: /money <set/take/add> <player> <amount>",em.prefix), TextColor.fromHexString("#db3021")));
            return false;
        }

        OfflinePlayer reciever = Bukkit.getOfflinePlayer(args[1]);
        if(!reciever.hasPlayedBefore() && !reciever.isOnline()) {
            sender.sendMessage(Component.text(String.format("[%s] That player doesn't exist!",em.prefix),TextColor.fromHexString("#db3021")));
            return false;
        }
        try{
            int amount = Integer.parseInt(args[2]);
            if(args[0].equalsIgnoreCase("add")) {
                em.addMoney(reciever.getUniqueId(),amount);
                if(reciever.isOnline() && em.showMoneyOnTab) {
                    Player p = Bukkit.getPlayerExact(args[1]);
                    Objects.requireNonNull(p).playerListName(Component.text(p.displayName() + "    ").append(Component.text(em.getMoney(p.getUniqueId()) + em.currencySymbol, TextColor.fromCSSHexString("#80f27e"))));
                }
                sender.sendMessage(Component.text(String.format("[%s] Added %d %s to %s",em.prefix,amount,em.currencySymbol,args[1]),TextColor.fromHexString("#30e607")));
            }
            else if(args[0].equalsIgnoreCase("take")) {
                em.removeMoney(reciever.getUniqueId(),amount);
                if(reciever.isOnline() && em.showMoneyOnTab) {
                    Player p = Bukkit.getPlayerExact(args[1]);
                    Objects.requireNonNull(p).playerListName(Component.text(p.displayName() + "    ").append(Component.text(em.getMoney(p.getUniqueId()) + em.currencySymbol, TextColor.fromCSSHexString("#80f27e"))));
                }
                sender.sendMessage(Component.text(String.format("[%s] Taken %d %s from %s",em.prefix,amount,em.currencySymbol,args[1]),TextColor.fromHexString("#30e607")));
            }
            else if(args[0].equalsIgnoreCase("set")) {
                em.setMoney(reciever.getUniqueId(),amount);
                if(reciever.isOnline() && em.showMoneyOnTab) {
                    Player p = Bukkit.getPlayerExact(args[1]);
                    Objects.requireNonNull(p).playerListName(Component.text(p.displayName() + "    ").append(Component.text(em.getMoney(p.getUniqueId()) + em.currencySymbol, TextColor.fromCSSHexString("#80f27e"))));
                }
                sender.sendMessage(Component.text(String.format("[%s] Set %d %s to %s",em.prefix,amount,em.currencySymbol,args[1]),TextColor.fromHexString("#30e607")));
            }
        } catch (Exception e) {
            sender.sendMessage(Component.text(String.format("[%s] Encountered an error! Check the console for details!", TextColor.fromHexString("#db3021"))));
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 1) {
            return List.of("add","take","set");
        }
        if (args.length == 2){
            List<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
            List<String> output = new ArrayList<>();
            for (Player pO : onlinePlayers) {
                output.add(pO.getName());
            }
            return output;
        }
        if (args.length == 3) {
            return List.of("1","5","20");
        }
        return null;
    }
}
