package br.com.fallcraft.kitsplus.utils;

import br.com.fallcraft.kitsplus.core.KitPlus;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class KitConfig {
    public static KitPlus plugin;
    private static File file;
    private static FileConfiguration userfile;

    public KitConfig(final KitPlus main) {
        KitConfig.plugin = main;
    }

    public static void setupKitFile(final KitPlus main) {
        KitConfig.plugin = main;
        if (!KitConfig.plugin.getDataFolder().exists()) {
            KitConfig.plugin.getDataFolder().mkdir();
        }

        KitConfig.file = new File(KitConfig.plugin.getDataFolder(), "kit-config.yml");

        if (!KitConfig.file.exists()) {
            try {
                KitConfig.plugin.saveResource("kit-config.yml", false);
            } catch (Exception localException1) {
                Bukkit.getConsoleSender().sendMessage("\ufffdcN\ufffdo foi poss\ufffdvel criar o arquivo kit-config.yml!");
                localException1.printStackTrace();
            }
        }
        KitConfig.userfile = YamlConfiguration.loadConfiguration(KitConfig.file);
    }

    public static void reload() {
        KitConfig.userfile = YamlConfiguration.loadConfiguration(KitConfig.file);
    }

    public static FileConfiguration getKitFIle() {
        return KitConfig.userfile;
    }

    public static void save() {
        try {
            KitConfig.userfile.save(KitConfig.file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
