package com.gachagame.refinement;

import com.gachagame.character.Character;
import com.gachagame.weapon.Weapon;

public class MergeHandler {

    //Checks if the characters all have the same rarity and name
    public boolean isValid(Character characterC, Character characterA, Character characterB) {
        String nameC = characterC.getName();
        String nameA = characterA.getName();
        String nameB = characterB.getName();
        
        if(nameC.equals(nameA) && nameC.equals(nameB))
            if(characterC.getRarity() == characterA.getRarity()
                    && characterC.getRarity() == characterB.getRarity())
                if(characterC.getRarity() != 5)
                    return true;

        return false;
    }

    //Checks if the weapons all have the same rarity and name
    public boolean isValid(Weapon weaponC, Weapon weaponA, Weapon weaponB) {
        String nameC = weaponC.getName();
        String nameA = weaponC.getName();
        String nameB = weaponC.getName();
        
        if(nameC.equals(nameA) && nameC.equals(nameB))
            if(weaponC.getRarity() == weaponA.getRarity()
                    && weaponC.getRarity() == weaponB.getRarity())
                if(weaponC.getRarity() != 5)
                    return true;

        return false;
    }

    //characterC - final merged character
    public Character mergeCharacter(Character characterC, Character characterA, Character characterB) {
        characterA.unequip();
        characterB.unequip();
        characterC.setRarity(characterC.getRarity() + 1);

        return characterC;
    }

    //weaponC - final merged weapon
    //to be reviewed
    public Weapon mergeWeapon(Weapon weaponC, Weapon weaponA, Weapon weaponB) {
        weaponC.setRarity(weaponC.getRarity() + 1);

        return weaponC;
    }


}
