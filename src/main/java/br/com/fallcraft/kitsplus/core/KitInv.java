package br.com.fallcraft.kitsplus.core;

public class KitInv {
    private String kitName;
    private String kitPerm;
    private boolean enchant;
    private String icon;
    private short type;
    private int pos;
    private int time;

    public KitInv(String kitName, String kitPerm, boolean enchant, String icon, short type, int pos, int time) {
        this.kitName = kitName;
        this.kitPerm = kitPerm;
        this.enchant = enchant;
        this.icon = icon;
        this.type = type;
        this.pos = pos;
        this.time = time;
    }

    public String getKitName() {
        return kitName;
    }

    public void setKitName(String kitName) {
        this.kitName = kitName;
    }

    public String getKitPerm() {
        return kitPerm;
    }

    public void setKitPerm(String kitPerm) {
        this.kitPerm = kitPerm;
    }

    public boolean isEnchant() {
        return enchant;
    }

    public void setEnchant(boolean enchant) {
        this.enchant = enchant;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
