package br.com.fallcraft.kitsplus.core;

import br.com.fallcraft.kitsplus.commands.CreateKit;
import br.com.fallcraft.kitsplus.commands.DeleteKit;
import br.com.fallcraft.kitsplus.commands.Kit;
import br.com.fallcraft.kitsplus.listeners.ClickAndGetItem;
import br.com.fallcraft.kitsplus.listeners.CloseAndCreateKit;
import br.com.fallcraft.kitsplus.utils.KitConfig;
import br.com.fallcraft.kitsplus.utils.KitDbConfig;
import br.com.fallcraft.kitsplus.utils.Ultilities;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class KitPlus extends JavaPlugin {
    public static KitPlus kitPlus;

    @Override
    public void onEnable() {
        KitPlus.kitPlus = this;
        load();

        Bukkit.getConsoleSender().sendMessage(Ultilities.formater("&9[&2Kits&5&lPlus&9] &aCarregado com sucesso!"));
        Bukkit.getConsoleSender().sendMessage(Ultilities.formater("&9[&2Kits&5&lPlus&9] &3LoginFallCraft: Criado por 1Urso (1Urso#8730)"));
    }


    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Ultilities.formater("&9[&2Kits&5&lPlus&9] &c&lCarregado com sucesso!"));
        HandlerList.unregisterAll(this);
    }

    private void load() {
        KitConfig.setupKitFile(this);
        KitDbConfig.setupDBKit(this);
        new ClickAndGetItem(this);
        new CloseAndCreateKit(this);
        new CreateKit(this);
        new DeleteKit(this);
        new Kit(this);
    }


}
