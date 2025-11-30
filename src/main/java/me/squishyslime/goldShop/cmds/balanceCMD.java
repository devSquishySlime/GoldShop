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
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class balanceCMD implements CommandExecutor {
    private final EconomyManager em;
    public balanceCMD(Main plugin) {
        this.em = plugin.em;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length == 1) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
            if(!player.hasPlayedBefore() && !player.isOnline()) {
                sender.sendMessage(Component.text("[%s] That player doesn't exist!", TextColor.fromHexString("#db3021")));
                return false;
            }
            sender.sendMessage(Component.text(String.format("[%s] %s's balance: %d %s",em.prefix,args[0],em.getMoney(player.getUniqueId()),em.currencySymbol),TextColor.fromHexString("#30e607")));
        }
        else {
            if (sender instanceof Player player) {
                sender.sendMessage(Component.text(String.format("[%s] Your balance: %d %s",em.prefix,em.getMoney(player.getUniqueId()),em.currencySymbol),TextColor.fromHexString("#30e607")));

            }
        }
        return true;
    }
}
