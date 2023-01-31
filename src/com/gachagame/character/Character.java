package com.gachagame.character;

import com.gachagame.weapon.Weapon;

public class Character {
    private String name;
    private int rarity;
    private String element;
    private int level;
    private String type;
    private Weapon equippedWeapon;

    public Character(String name, int rarity, String element, int level, String type) {
        this.name = name;
        this.rarity = rarity;
        this.element = element;
        this.level = level;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public int getRarity() {
        return this.rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public String getElement() {
        return this.element;
    }

    public int getElementAsInt() {
        String elem = this.element;

        if (elem.equals("Joker")) return 0;
        else if (elem.equals("Trigger")) return 1;
        else if (elem.equals("Metal")) return 2;
        else if (elem.equals("Cyclone")) return 3;
        else if (elem.equals("Luna")) return 4;
        else if (elem.equals("Heat")) return 5;

        return -1;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Weapon getWeapon() {
        return this.equippedWeapon;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void equip(Weapon w) {
        if (!getType().equals(w.getType())) {
            System.out.println("Equip failed. Character class does not match Weapon type");
            return;
        }

        if (getWeapon() != null) unequip();
        if (w.getEquippedTo() != null) w.getEquippedTo().unequip();

        this.equippedWeapon = w;
        this.equippedWeapon.setEquippedTo(this);
    }

    public void unequip() {
        try {
            this.equippedWeapon.setEquippedTo(null);
            this.equippedWeapon = null;
        } catch (NullPointerException e) {
            System.out.println(String.format("%s has no weapon equipped.", getName()));
        }
    }
}
