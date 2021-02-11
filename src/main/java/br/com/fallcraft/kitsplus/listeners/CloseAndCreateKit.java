package br.com.fallcraft.kitsplus.listeners;

import br.com.fallcraft.kitsplus.commands.CreateKit;
import br.com.fallcraft.kitsplus.core.KitInv;
import br.com.fallcraft.kitsplus.core.KitPlus;
import br.com.fallcraft.kitsplus.utils.*;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class CloseAndCreateKit implements Listener {
    public KitPlus plugin;

    public CloseAndCreateKit(KitPlus pl) {
        this.plugin = pl;
        plugin.getServer().getPluginManager().registerEvents(this, pl);
    }

    @EventHandler
    public void onCloseCreateKit(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (!event.getInventory().getName().contains("KIT")) {
            return;
        }
        String[] names = event.getInventory().getName().split("\\.");
        String name = names[1];

        KitInv kit = CreateKit.oppenedInventorysForKitCreation.get(name);
        Inventory inventory = event.getInventory();


        //String invent = SaveInventory.InventoryToString(inventory);
        //SaveInventory.save(plugin, invent, kit.getKitName());
        String inv = SerializeInv.toBase64(inventory);
        SerializeInv.save(plugin , inv , kit.getKitName());



        KitConfig.getKitFIle().set("kit." + kit.getKitName() + ".name", kit.getKitName());
        KitConfig.getKitFIle().set("kit." + kit.getKitName() + ".icon", kit.getIcon());
        KitConfig.getKitFIle().set("kit." + kit.getKitName() + ".type", kit.getType());
        KitConfig.getKitFIle().set("kit." + kit.getKitName() + ".perm", kit.getKitPerm());
        KitConfig.getKitFIle().set("kit." + kit.getKitName() + ".effect", kit.isEnchant());
        KitConfig.getKitFIle().set("kit." + kit.getKitName() + ".pos", kit.getPos());
        KitConfig.getKitFIle().set("kit." + kit.getKitName() + ".time", kit.getTime());
        KitConfig.save();

        player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aKit &6" + kit.getKitName() + " &acriado com sucesso!"));

    }

}
