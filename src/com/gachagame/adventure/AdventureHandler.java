package com.gachagame.adventure;

import java.util.ArrayList;
import java.util.Scanner;

import com.gachagame.character.Character;
import com.gachagame.player.*;
import com.gachagame.weapon.Magical;

public class AdventureHandler {
   private ArrayList<Map> mapList; 

   public AdventureHandler() {
       this.mapList = new ArrayList<Map>();

       // Adding Maps
       addMap("Underground Caverns", 53);
       this.mapList.get(0).addEnemy("Elf", 224, 1);
       this.mapList.get(0).addEnemy("Slime", 73, 6);
       
       addMap("Forest of Enchantments", 77);
       this.mapList.get(1).addEnemy("Slime", 73, 5);
       this.mapList.get(1).addEnemy("Orc", 84, 5);
       this.mapList.get(1).addEnemy("Familiar", 144, 3);
       this.mapList.get(1).addEnemy("Faerie", 175, 3);
       this.mapList.get(1).addEnemy("Elf", 224, 2);
       this.mapList.get(1).addEnemy("Sorcerer", 313, 1);

       addMap("Sea of Hope", 85);
       this.mapList.get(2).addEnemy("Slime", 73, 75);
       this.mapList.get(2).addEnemy("Sorcerer", 313, 20);
       this.mapList.get(2).addEnemy("Hydra", 360, 5);

       addMap("Tower of Ether", 91);
       this.mapList.get(3).addEnemy("Basilisk", 499, 20);
       this.mapList.get(3).addEnemy("Harpy", 639, 7);
       this.mapList.get(3).addEnemy("Loki", 740, 5);

       addMap("Celestial Plane", 100);
       this.mapList.get(4).addEnemy("Faerie", 175, 50);
       this.mapList.get(4).addEnemy("Hydra", 360, 20);
       this.mapList.get(4).addEnemy("Loki", 740, 10);
   }

   public ArrayList<Map> getMapList() {
       return this.mapList;
   }

   public void addMap(String name, int baseAmount) {
       this.mapList.add(new Map(name, baseAmount));
   }

   public void selectMap(Character[] party, Resource r) {
       Scanner input = new Scanner(System.in);
       int mapIndex = -1;
       int levelUpVal;

       // print available maps here and set the input to mapIndex
       System.out.println(String.format("%-60s", " ").replace(" ", "-"));
       System.out.println(String.format("%41s", "Adventure Map Selection"));
       System.out.println(String.format("%-60s", " ").replace(" ", "-"));
       System.out.println(String.format("%20s%20s  %s", "Name", "Resources", "Enemies"));
       for (int i = 0; i < getMapList().size(); i++) {
           System.out.print(String.format("[%d] %20s", i + 1, getMapList().get(i).getName()));
           System.out.print(String.format("%10d", getMapList().get(i).getBaseAmount()));
           for (int j = 0; j < getMapList().get(i).getEnemyList().size(); j++) {
               if (j == 0) {
                   System.out.println(String.format("%12s x %d", getMapList().get(i).getEnemyList().get(j).getName(), getMapList().get(i).getEnemyList().get(j).getQuantity()));
               } else {
                   System.out.println(String.format("%47s x %d", getMapList().get(i).getEnemyList().get(j).getName(), getMapList().get(i).getEnemyList().get(j).getQuantity()));
               }
           }
       }

        while (mapIndex < 0 || mapIndex > 4) {
            System.out.println("Select a map by typing it's number: ");
            while (!input.hasNextInt()) {
                input.next();
            }
            mapIndex = input.nextInt() - 1;
        }

        float partyCombination = getElementPairing(party);
        if (partyCombination == 1.10f) {
            System.out.println("Your party has a NORMAL pairing!");
        } else if (partyCombination == 1.75f) {
            System.out.println("Your party has a PERFECT pairing!");
        } else if (partyCombination == 1.50f) {
            System.out.println("Your party has a NICE pairing!");
        } else if (partyCombination == 1.25f) {
            System.out.println("Your party has a DECENT pairing!");
        } else if (partyCombination == 1.00f) {
            System.out.println("Your party's pairing has NO EFFECT!");
        } else {
            System.out.println("Your party has a BAD pairing!");
        }

        int totalResource = computeResource(party, this.mapList.get(mapIndex));
        System.out.println("Resources gained:");
        System.out.println("Anima: " + (int)(totalResource * 0.7f));
        System.out.println("Refina: " + totalResource);
        r.addAnima(totalResource);
        r.addRefina(totalResource);
        levelUpVal = compareSuperiority(getSuperiority(party), getSuperiority(this.mapList.get(mapIndex).getEnemyList()));

        if (levelUpVal != 0)
            System.out.println("Party members have leveled up " + levelUpVal + " times!");
            for (int i = 0; i < 2; i++) party[i].setLevel(party[i].getLevel() + levelUpVal);
   }

   public int compareSuperiority(float playerSuperiority, float enemySuperiority) {
       if (playerSuperiority > enemySuperiority * 1.50f) {
           return 2;
       } else if (playerSuperiority > enemySuperiority) {
           return 1;
       }

       return 0;
   }

   public int computeResource(Character[] party, Map selectedMap) {
       return (int)(selectedMap.getBaseAmount() + (int)(getFinalWeaponPower(party) / 24)) * (int)((getTotalCharacterInfluence(party) / 36) * getElementPairing(party));
   }

   public float getSuperiority(Character[] party) {
       return getFinalWeaponPower(party) * (getTotalCharacterInfluence(party) / 10);
   }

   public float getSuperiority(ArrayList<Enemy> enemy) {
       int enemySuperiority = 0;
       for (int i = 0; i < enemy.size(); i++) {
           enemySuperiority += enemy.get(i).getTotalPower();
       }

       return enemySuperiority;
   }

   public float getElementPairing(Character[] party) {
       final float NORMAL = 1.10f, PERFECT = 1.75f, NICE = 1.50f, DECENT = 1.25f, NONE = 1.00f, BAD = 0.75f;
       float[][] pairings = {
           {NORMAL, DECENT, DECENT, PERFECT, NICE, NICE},
           {DECENT, NORMAL, NONE, NONE, PERFECT, NONE},
           {DECENT, NONE, NORMAL, DECENT, DECENT, PERFECT},
           {PERFECT, NONE, DECENT, NORMAL, BAD, BAD},
           {NICE, PERFECT, DECENT, BAD, NORMAL, BAD},
           {NICE, NONE, PERFECT, BAD, BAD, NORMAL}
       };

       if (party[0].getElementAsInt() == -1 || party[1].getElementAsInt() == -1) {
           System.out.println("Error: Invalid element as modifier");
           return -1;
       }

       // Advanced Magic Weapon Check
       if (party[0].getWeapon() instanceof Magical || party[1].getWeapon() instanceof Magical) {
           float bestPair;
           ArrayList<Integer> elem = new ArrayList<Integer>();
           elem.add(party[0].getElementAsInt());
           elem.add(party[1].getElementAsInt());

           // add to elem if character has magical weapon
           for (int i = 0; i < 2; i++) {
               if (party[i].getWeapon() instanceof Magical) {
                   elem.add(((Magical) (party[i].getWeapon())).getElementAsInt());
               }
           }

           // pair every element to one another
           bestPair = pairings[party[0].getElementAsInt()][party[1].getElementAsInt()];
           
           for (int i = 0; i < elem.size(); i++) {
               for (int j = i + 1; j < elem.size(); j++) {
                   float temp =  pairings[elem.get(i)][elem.get(j)];

                   if (temp > bestPair) {
                       bestPair = temp;
                   }
               }
           }

           return bestPair;
       }

       return pairings[party[0].getElementAsInt()][party[1].getElementAsInt()];
   }

   public float getFinalWeaponPower(Character[] party) {
       float finalPower = 0.0f;

       for (int i = 0; i < 2; i++) {
           finalPower += party[i].getWeapon().getPower() * party[i].getWeapon().getRarityMultiplier() + party[i].getWeapon().getLevel();
       }

       return finalPower;
   }

   public float getTotalCharacterInfluence(Character[] party) {
       // bizarre inexact floating point multiplication here
       // have to explicitly convert to float for it to work
       float influenceA = (float)party[0].getLevel() * (float)(1.0f + (float)((float)(party[0].getRarity() - 1.0f) / 5.0f));
       float influenceB = (float)party[1].getLevel() * (float)(1.0f + (float)((float)(party[1].getRarity() - 1.0f) / 5.0f));

       return (float)(influenceA + influenceB);
   }
}
