package br.com.fallcraft.kitsplus.utils;

import br.com.fallcraft.kitsplus.core.KitPlus;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.*;
import java.util.Map;

public class SaveInventory {
    public static void save(KitPlus plugin, String a, Player player) {
        try {
            File fl = new File(plugin.getDataFolder() + "/inventorys/");
            if (!fl.exists()) {
                fl.mkdir();
            }
            FileOutputStream f = new FileOutputStream(plugin.getDataFolder() + "/inventorys/" + player.getUniqueId() + ".txt");
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(a);

            o.close();
            f.close();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static String recovery(KitPlus plugin, Player player) {
        String pr1 = null;
        try {
            FileInputStream fi = new FileInputStream(plugin.getDataFolder() + "/inventorys/" + player.getUniqueId() + ".txt");
            ObjectInputStream oi = new ObjectInputStream(fi);

            pr1 = (String) oi.readObject();


            oi.close();
            fi.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return pr1;
    }

    public static void save(KitPlus plugin, String a, String title) {
        try {
            File fl = new File(plugin.getDataFolder() + "/kits/");
            if (!fl.exists()) {
                fl.mkdir();
            }
            FileOutputStream f = new FileOutputStream(plugin.getDataFolder() + "/kits/" + title + ".txt");
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(a);

            o.close();
            f.close();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static String recovery(KitPlus plugin, String title) {
        String pr1 = null;
        try {
            FileInputStream fi = new FileInputStream(plugin.getDataFolder() + "/kits/" + title + ".txt");
            ObjectInputStream oi = new ObjectInputStream(fi);

            pr1 = (String) oi.readObject();


            oi.close();
            fi.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return pr1;
    }


    public static String InventoryToString(Inventory invInventory) {
        String serialization = invInventory.getSize() + ";";
        for (int i = 0; i < invInventory.getSize(); i++) {
            ItemStack is = invInventory.getItem(i);
            if (is != null) {
                String serializedItemStack = "";

                String isType = String.valueOf(is.getType().getId());
                serializedItemStack += "t@" + isType;

                if (is.getDurability() != 0) {
                    String isDurability = String.valueOf(is.getDurability());
                    serializedItemStack += ":d@" + isDurability;
                }

                if (is.getAmount() != 1) {
                    String isAmount = String.valueOf(is.getAmount());
                    serializedItemStack += ":a@" + isAmount;
                }

                Map<Enchantment, Integer> isEnch = is.getEnchantments();
                if (isEnch.size() > 0) {
                    for (Map.Entry<Enchantment, Integer> ench : isEnch.entrySet()) {
                        serializedItemStack += ":e@" + ench.getKey().getId() + "@" + ench.getValue();
                    }
                }

                serialization += i + "#" + serializedItemStack + ";";
            }
        }
        return serialization;
    }


    public static Inventory StringToInventory(String invString) {
        String[] serializedBlocks = invString.split(";");
        String invInfo = serializedBlocks[0];
        Inventory deserializedInventory = Bukkit.getServer().createInventory(null, Integer.parseInt(invInfo));

        for (int i = 1; i < serializedBlocks.length; i++) {
            String[] serializedBlock = serializedBlocks[i].split("#");
            int stackPosition = Integer.parseInt(serializedBlock[0]);

            if (stackPosition >= deserializedInventory.getSize()) {
                continue;
            }

            ItemStack is = null;
            boolean createdItemStack = false;

            String[] serializedItemStack = serializedBlock[1].split(":");
            for (String itemInfo : serializedItemStack) {
                String[] itemAttribute = itemInfo.split("@");
                if (itemAttribute[0].equals("t")) {
                    is = new ItemStack(Material.getMaterial(Integer.parseInt(itemAttribute[1])));
                    createdItemStack = true;
                } else if (itemAttribute[0].equals("d") && createdItemStack) {
                    is.setDurability(Short.parseShort(itemAttribute[1]));
                } else if (itemAttribute[0].equals("a") && createdItemStack)
                    is.setAmount(Integer.parseInt(itemAttribute[1]));
                else if (itemAttribute[0].equals("e") && createdItemStack) {
                    is.addEnchantment(Enchantment.getById(Integer.parseInt(itemAttribute[1])), Integer.parseInt(itemAttribute[2]));
                }
            }
            deserializedInventory.setItem(stackPosition, is);
        }

        return deserializedInventory;
    }


}
