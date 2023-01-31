package com.gachagame.weapon;

import com.gachagame.character.Character;

public abstract class Weapon {
    protected String name;
    protected int power;
    protected int rarity;
    protected int level;
    protected String type;
    protected Character equippedTo;

    public Weapon(String name, int rarity, int power, int level, String type) {
        this.name = name;
        this.power = power;
        this.rarity = rarity;
        this.level = level;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public int getPower() {
        return power;
    }

    public int getRarity() {
        return this.rarity;
    }

    public float getRarityMultiplier() {
        if (getRarity() == 2) return 0.8f;
        else if (getRarity() == 3) return 0.9f;
        else if (getRarity() == 4) return 1.0f;
        else if (getRarity() == 5) return 1.2f;

        return 0.7f;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Character getEquippedTo() {
        return this.equippedTo;
    }

    public void setEquippedTo(com.gachagame.character.Character character) {
        this.equippedTo = character;
    }
}
