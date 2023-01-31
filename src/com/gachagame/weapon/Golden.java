package com.gachagame.weapon;

import java.io.FileNotFoundException;

import com.gachagame.gacha.GachaHandler;
import com.gachagame.player.Resource;

public class Golden extends Bladed {

    public Golden(String name, int rarity, int power, int level, String type) {
        super(name, rarity, power, level, type);
    }

    public Weapon reroll(Resource r, GachaHandler g) throws FileNotFoundException {
        if (!r.useRefina(150)) {
            System.out.println("Reroll failed. Insufficient resources!");
            return this;
        }

        return g.pullWeapon();
    }
}
