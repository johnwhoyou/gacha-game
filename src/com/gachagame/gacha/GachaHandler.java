package com.gachagame.gacha;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.gachagame.character.Character;
import com.gachagame.weapon.*;
import com.gachagame.weapon.Weapon;

public class GachaHandler {
    public Character pullCharacter() throws FileNotFoundException {
        Character character;
        Path path = Path.of("src/com/gachagame/gacha/CharacterData.txt");
        Scanner input = new Scanner(path.toAbsolutePath().toFile());
            
        Random rand = new Random();
        float random = rand.nextFloat();
        
        System.out.println("Pulling Character...");

        // check if rand qualifies to be a rarity 2 or 3 roll
        if (random >= 0.15f && random < 0.50f) {
            for (int i = 0; i < rand.nextInt(12 - 6 + 1) + 6; i++) {
                input.nextLine();
            }
        } else if (random < 0.15f) {
            // return random rarity three character
            for (int i = 0; i < rand.nextInt(18 - 12 + 1) + 12; i++) {
                input.nextLine();
            }
        } else {
            // return random rarity one character otherwise
            for (int i = 0; i < rand.nextInt(5 + 1); i++) {
                input.nextLine();
            }
        }
        String name = input.next().replace("+", " ");
        character = new Character(name, input.nextInt(), input.next(), 20, input.next());
        System.out.println(String.format("You pulled: %s\nRarity: %d\nElement: %s\nLevel: %d\nType: %s", character.getName(), character.getRarity(), character.getElement(), character.getLevel(), character.getType()));
        input.close();

        return character;
    }

    public ArrayList<Character> pullCharacter10() throws FileNotFoundException {
        ArrayList<Character> list = new ArrayList<Character>();

        System.out.println("Pulling 10 Characters...");

        // repeat single pull 10 times
        for (int i = 0; i < 10; i++) {
            list.add(pullCharacter());
        }

        return list;
    }

    public Weapon pullWeapon() throws FileNotFoundException {
        Weapon weapon;
        Path path = Path.of("src/com/gachagame/gacha/WeaponData.txt");
        Scanner input = new Scanner(path.toAbsolutePath().toFile());
        Random rand = new Random();
        float random = rand.nextFloat();

        System.out.println("Pulling Weapon...");

        // check if rand qualifies to be a rarity 2 or 3 roll
        if (random >= 0.15f && random < 0.50f) {
            // return random rarity two weapon
            for (int i = 0; i < rand.nextInt(12 - 6 + 1) + 6; i++) {
                input.nextLine();
            }
        } else if (random < 0.15f) {
            // return random rarity three weapon
            for (int i = 0; i < rand.nextInt(18 - 12 + 1) + 12; i++) {
                input.nextLine();
            }
        } else {
            // return random rarity one weapon otherwise
            for (int i = 0; i < rand.nextInt(5 + 1); i++) {
                input.nextLine();
            }
        }
        String name = input.next().replace("+", " ");
        int rarity = input.nextInt();
        int power = input.nextInt();
        int level = 1;
        String type = input.next();
        weapon = new Bladed(name, rarity, power, level, type); // placeholder object

        switch (type) {
            case "Bladed":
                break;
            case "Magical":
                String advancedElem = input.next();
                weapon = new Magical(name, rarity, power, level, type, advancedElem);
                break;
            case "Ranged":
                weapon = new Ranged(name, rarity, power, level, type);
                break;
            case "Golden":
                weapon = new Golden(name, rarity, power, level, type);
                break;
            default:
                System.out.println("Error: Weapon incorrectly instantiated");
        }
        
        System.out.println(String.format("You pulled: %s\nPower: %d\nRarity: %d\nLevel: %d\nType: %s", weapon.getName(), weapon.getPower(), weapon.getRarity(), weapon.getLevel(), weapon.getType()));
        input.close();

        return weapon;
    }

    public ArrayList<Weapon> pullWeapon10() throws FileNotFoundException {
        ArrayList<Weapon> list = new ArrayList<Weapon>();

        System.out.println("Pulling 10 Weapons...");

        // repeat single pull 10 times
        for (int i = 0; i < 10; i++) {
            list.add(pullWeapon());
        }

        return list;
    }


}
