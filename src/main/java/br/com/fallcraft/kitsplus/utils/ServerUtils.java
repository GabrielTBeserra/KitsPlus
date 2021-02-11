package br.com.fallcraft.kitsplus.utils;

import com.sun.security.auth.login.ConfigFile;
import org.bukkit.entity.Player;
import sun.security.krb5.Config;

import java.util.ArrayList;
import java.util.List;

public class ServerUtils {
    public static String PLUGIN_NAME;
    public static int MENU_SIZE;
    public static String SERVER_NAME;
    public static List<Player> vanishList;

    static {
        ServerUtils.PLUGIN_NAME = "&9[&2Kits&5&lPlus&9] &9&l>>&f ";
        ServerUtils.MENU_SIZE = KitConfig.getKitFIle().getInt("size");
        ServerUtils.SERVER_NAME = "&9[&2Kits&5&lPlus&9] &9&l>>&f ";
        ServerUtils.vanishList = new ArrayList<Player>();
    }

}