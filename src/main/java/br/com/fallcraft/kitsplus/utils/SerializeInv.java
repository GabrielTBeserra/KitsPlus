package br.com.fallcraft.kitsplus.utils;

import br.com.fallcraft.kitsplus.core.KitPlus;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.*;

public class SerializeInv {
    public static String itemStackArrayToBase64(ItemStack[] items) throws IllegalStateException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            // Write the size of the inventory
            dataOutput.writeInt(items.length);

            // Save every element in the list
            for (int i = 0; i < items.length; i++) {
                dataOutput.writeObject(items[i]);
            }

            // Serialize that array
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }

    public static String toBase64(Inventory inventory) throws IllegalStateException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            // Write the size of the inventory
            dataOutput.writeInt(inventory.getSize());

            // Save every element in the list
            for (int i = 0; i < inventory.getSize(); i++) {
                dataOutput.writeObject(inventory.getItem(i));
            }

            // Serialize that array
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }

    public static ItemStack[] itemStackArrayFromBase64(String data) throws IOException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            ItemStack[] items = new ItemStack[dataInput.readInt()];

            // Read the serialized inventory
            for (int i = 0; i < items.length; i++) {
                items[i] = (ItemStack) dataInput.readObject();
            }

            dataInput.close();
            return items;
        } catch (ClassNotFoundException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }


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
}
