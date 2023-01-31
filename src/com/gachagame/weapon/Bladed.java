package com.gachagame.weapon;

public class Bladed extends Weapon {

    public Bladed(String name, int rarity, int power, int level, String type) {
        super(name, rarity, power, level, type);
    }
    
    @Override
    public int getPower() {
        return super.getPower() + (10 * getRarity());
    }
}
