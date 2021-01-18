package br.com.fallcraft.kitsplus.commands;

import br.com.fallcraft.kitsplus.core.KitPlus;
import br.com.fallcraft.kitsplus.utils.KitConfig;
import br.com.fallcraft.kitsplus.utils.ServerUtils;
import br.com.fallcraft.kitsplus.utils.Ultilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteKit implements CommandExecutor {
    public KitPlus plugin;

    public DeleteKit(KitPlus plugin) {
        this.plugin = plugin;
        plugin.getCommand("delkit").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command for only players!");
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&c&lEsse kit nao existe!"));
            return true;
        }


        KitConfig.getKitFIle().set("kit." + args[0].toLowerCase(), null);
        KitConfig.save();
        sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&c&lKit deletado!"));
        return true;
    }
}
