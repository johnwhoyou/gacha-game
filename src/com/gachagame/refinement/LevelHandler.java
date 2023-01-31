package com.gachagame.refinement;

import com.gachagame.character.Character;
import com.gachagame.weapon.Ranged;
import com.gachagame.weapon.Weapon;

public class LevelHandler {

    /*
    Validates if the character is below max level
    */
    public boolean isValid(Character character) {
        return character.getLevel() < 100;
    }

    /*
    Validates if the weapon is below max weapon level
    */
    public boolean isValid(Weapon weapon) {
        return weapon.getLevel() < 50;
    }

    public Character levelCharacter(Character character, int amount) {
        character.setLevel(character.getLevel() + amount);

        return character;
    }

    public Weapon levelWeapon(Weapon weapon, int amount) {
        weapon.setLevel(weapon.getLevel() + amount);

        return weapon;
    }

    public void hone(Ranged w) {
        w.hone();
    }
}
