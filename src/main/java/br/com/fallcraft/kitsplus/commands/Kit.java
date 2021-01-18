package br.com.fallcraft.kitsplus.commands;

import br.com.fallcraft.kitsplus.core.KitPlus;
import br.com.fallcraft.kitsplus.utils.KitConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kit implements CommandExecutor {
    public KitPlus plugin;

    public Kit(KitPlus plugin) {
        this.plugin = plugin;
        plugin.getCommand("kit").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command for only players!");
            return true;
        }

        Player player = (Player) sender;

        try {
            KitMenu.openInv(player, KitConfig.getKitFIle());
        } catch (Exception e) {
            e.printStackTrace();
        }


        return true;
    }
}
