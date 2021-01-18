package br.com.fallcraft.kitsplus.commands;


import br.com.fallcraft.kitsplus.core.KitInv;
import br.com.fallcraft.kitsplus.core.KitPlus;
import br.com.fallcraft.kitsplus.utils.ServerUtils;
import br.com.fallcraft.kitsplus.utils.Ultilities;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class CreateKit implements CommandExecutor {
    public static Map<String, KitInv> oppenedInventorysForKitCreation = new HashMap<String, KitInv>();
    public KitPlus plugin;

    public CreateKit(KitPlus plugin) {
        this.plugin = plugin;
        plugin.getCommand("createkit").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command for only players!");
            return true;
        }

        // Check args length
        if (args.length < 2) {
            return false;
        }

        Player player = (Player) sender;
        String kitName = args[0].toLowerCase();
        String kitPerm = "kitplus.kit.default";
        boolean isEnchanted = player.getInventory().getItemInHand().getEnchantments().size() > 0;
        String itemName = player.getInventory().getItemInHand().getType().toString();
        short type = player.getInventory().getItemInHand().getDurability();
        int iconPos = 0;
        int time = 1;

        // Em caso nao tiver nenhum item na mao, coloca GRASS como padrao
        if (player.getInventory().getItemInHand().getType().equals(Material.AIR)) {
            itemName = "GRASS";
        }

        // Adiciona permissao personalizada ao plugin
        if (args.length == 4) {
            kitPerm = "kitplus.kit." + args[3].toLowerCase();
        }

        try {
            iconPos = Integer.parseInt(args[1]);
            time = Integer.parseInt(args[2]);
        } catch (Exception e) {
            sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&c&lVoce precisa informar a posicao do item!"));
            return false;
        }

        oppenedInventorysForKitCreation.put(kitName, new KitInv(kitName, kitPerm, isEnchanted, itemName, type, iconPos, time));

        Inventory inv = Bukkit.createInventory(player, 27, Ultilities.formater("KIT." + kitName));
        player.openInventory(inv);

        return true;
    }
}
