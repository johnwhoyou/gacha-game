package com.gachagame.player;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.gachagame.adventure.*;
import com.gachagame.gacha.GachaHandler;
import com.gachagame.refinement.*;
import com.gachagame.character.Character;
import com.gachagame.weapon.*;

public class Player {
    private ArrayList<Character> characterList;
    private ArrayList<Weapon> weaponList;
    private Character[] party;
    private Resource resources;

    public Player() {
        this.characterList = new ArrayList<Character>();
        this.weaponList = new ArrayList<Weapon>();
        this.party = new Character[2];
        this.resources = new Resource(16750, 20000);
        
        // for debugging
        for (int i = 0; i < 2; i++) {
            // this.characterList.add(new Character("Jekyll", 1, "Joker", 20, "Bladed"));
            // this.weaponList.add(new Bladed("Knife", 1, 130, 1, "Bladed"));
            // this.weaponList.add(new Golden("Scythe of Father Time", 3, 230, 20, "Golden"));
            addCharacter(new Character("Count d'Artagnan", 1, "Metal", 20, "Magical"));

            weaponList.add(new Magical("Mermaid Tears", 1, 160, 1, "Magical", "Trigger"));
            weaponList.add(new Magical("Circe Staff", 2, 150, 1,"Magical" ,"Luna"));
            weaponList.add(new Magical("Vorpal Sword", 2, 160, 1,"Magical" ,"Metal"));
            weaponList.add(new Magical("Merlin's Staff", 2, 170, 1,"Magical" ,"Cyclone"));
            weaponList.add(new Magical("Philosopher's Stone", 5, 180, 1,"Magical" ,"Heat"));
        }
    }

    public void setPartyMembers() {
        Scanner scan = new Scanner(System.in);
        int characterIndex1 = -1;
        int characterIndex2 = -1;

        if (getCharacterList().size() < 2) {
            System.out.println("You are required to have at least 2 characters in your inventory. Returning to Main Menu.");
            return;
        }

        System.out.println(String.format("%-60s", " ").replace(" ", "-"));
        System.out.println(String.format("%34s", "Set Party"));
        System.out.println(String.format("%-60s", " ").replace(" ", "-"));
        System.out.println(String.format("%18s%21s%5s%5s%s%s", "Characters", "Rarity", "  Element", "  Level", "   Class", "      Equip"));

        printList(2);


        System.out.print("Enter valid character index for first party member: ");
        while (characterIndex1 < 0 || characterIndex1 > this.characterList.size()) {
            while (!scan.hasNextInt()) {
                scan.next();
            }
            characterIndex1 = scan.nextInt() - 1;
        }
        System.out.print("Enter valid character index for second party member: ");
        while (characterIndex2 < 0 || characterIndex2 > this.characterList.size()) {
            while (!scan.hasNextInt()) {
                scan.next();
            }
            characterIndex2 = scan.nextInt() - 1;
        }

        if (characterIndex1 == characterIndex2) {
            System.out.println("The party members must be 2 different characters. Returning to Main Menu.");
            return;
        }

        if (this.characterList.get(characterIndex1) != null && this.characterList.get(characterIndex2) != null) {
                if (this.characterList.get(characterIndex1).getWeapon() != null && this.characterList.get(characterIndex1).getWeapon() != null) {
                    this.party = new Character[]{this.characterList.get(characterIndex1), this.characterList.get(characterIndex2)};
                }
                else {
                    System.out.println("Characters must have an equipped weapon before they can join a party. Returning to Main Menu.");
                }
        }
        else {
            System.out.println("Invalid input. Returning to Main Menu.");
        }
    }

    public void manageInventory() {
        Scanner scan = new Scanner(System.in);
        int input = -1;

        System.out.println(String.format("%-60s", " ").replace(" ", "-"));
        System.out.println(String.format("%34s", "Inventory"));
        System.out.println(String.format("%-60s", " ").replace(" ", "-"));
        System.out.println(String.format("%18s%21s%5s%5s%s%s", "Characters", "Rarity", "  Element", "  Level", "   Class", "      Equip"));
        printList(2);
        System.out.print("\n\n");
        System.out.println(String.format("%18s%21s%5s%5s%s%s", "Weapons", "Rarity", "  Level", "  Power", "   Type", "             Equipped To"));
        printList(1);
        System.out.println(String.format("%-30s", " ").replace(" ", "-"));
        System.out.println("Anima: " + this.resources.getAnima());
        System.out.println("Refina: " + this.resources.getRefina());
        System.out.println(String.format("%-30s", " ").replace(" ", "-"));
        System.out.println("[1] Equip\n[2] Unequip\n[3] Return to Main Menu");

        while (input < 1 || input > 3) {
            while (!scan.hasNextInt()) {
                scan.next();
            }
            input = scan.nextInt();
        }
        

        if (input == 1) {
            int characterIndex = 0;
            int weaponIndex = 0;
            if (getCharacterList().isEmpty() || getWeaponList().isEmpty()) {
                System.out.println("There is nothing to equip!");
                return;
            }

            System.out.print("Enter valid character index: ");
            characterIndex = scan.nextInt() - 1;
            System.out.print("Enter valid weapon index: ");
            weaponIndex = scan.nextInt() - 1;

            if (characterIndex < getCharacterList().size() && weaponIndex < getWeaponList().size() && characterIndex >= 0 && weaponIndex >= 0) {
                getCharacterList().get(characterIndex).equip(getWeaponList().get(weaponIndex));
            } else {
                System.out.println("Invalid Input! Returning to Main Menu.");
                return;
            }
            
        } else if (input == 2) {
            int characterIndex = 0;
            if (getCharacterList().isEmpty() || getWeaponList().isEmpty()) {
                System.out.println("There is nothing to unequip!");
                return;
            }

            System.out.print("Enter valid character index: ");
            characterIndex = scan.nextInt() - 1;

            if (characterIndex < getCharacterList().size() && characterIndex >= 0 && getCharacterList().get(characterIndex).getWeapon() != null) {
                getCharacterList().get(characterIndex).unequip();
            } else {
                System.out.println("Invalid Input! Returning to Main Menu.");
                return;
            }
            
        } else {
            return;
        }
    }

    public void adventure(AdventureHandler a) {
        if(this.party[0] != null && this.party[1] != null) {
            if(this.party[0].getWeapon() != null && this.party[1].getWeapon() != null) {
                a.selectMap(this.party, this.resources);
            } else {
                System.out.println("All party members must have an equipped weapon!");
            }
        } else {
            System.out.println("Invalid party!");
        }
    }

    public void pull(GachaHandler g) {
        Scanner scan = new Scanner(System.in);
        int input = -1;

        System.out.println(String.format("%-60s", " ").replace(" ", "-"));
        System.out.println(String.format("%32s", "Gacha"));
        System.out.println(String.format("%-60s", " ").replace(" ", "-"));
        System.out.println("Choose Action:");
        System.out.println("[1] Pull Character (300 Anima)\n[2] Pull 10 Characters (2700 Anima)\n[3] Pull Weapon (300 Anima)\n[4] Pull 10 Weapons (2700 Anima)\n[5] Reroll Golden Weapon (150 Refina)\n[6] Back to Main Menu");

        while (input < 1 || input > 6) {
            while (!scan.hasNextInt()) {
                scan.next();
            }
            input = scan.nextInt();
        }

        if (input == 1) {
            if (this.resources.useAnima(300))
                try {
                    addCharacter(g.pullCharacter());
                } catch (FileNotFoundException e) {
                    System.out.println("Error: File not read!");
                    e.printStackTrace();
                }
            else
                System.out.println("Transaction Failed. Insufficient Resources!");
        } else if (input == 2) {
            if (this.resources.useAnima(2700))
                try {
                    addCharacter(g.pullCharacter10());
                } catch (FileNotFoundException e) {
                    System.out.println("Error: File not read!");
                    e.printStackTrace();
                }
            else
                System.out.println("Transaction Failed. Insufficient Resources!");
        } else if (input == 3) {
            if (this.resources.useAnima(300))
                try {
                    addWeapon(g.pullWeapon());
                } catch (FileNotFoundException e) {
                    System.out.println("Error: File not read!");
                    e.printStackTrace();
                }
            else
                System.out.println("Transaction Failed. Insufficient Resources!");
        } else if (input == 4) {
            if (this.resources.useAnima(2700))
                try {
                    addWeapon(g.pullWeapon10());
                } catch (FileNotFoundException e) {
                    System.out.println("Error: File not read!");
                    e.printStackTrace();
                }
            else
                System.out.println("Transaction Failed. Insufficient Resources!");
        } else if (input == 5) {
            int index = -1;
            printList(1);

            while (index < 0 || index > getWeaponList().size()) {
                while (!scan.hasNextInt()) {
                    scan.next();
                }
                index = scan.nextInt() - 1;
            }

            if (getWeaponList().get(index).getType().equals("Golden")) {
                // store weapon
                Weapon temp = getWeaponList().get(index);
                // remove weapon from weaponList
                getWeaponList().remove(temp);
                // add rerolled weapon back to weaponList
                try {
                    addWeapon(((Golden)temp).reroll(this.resources, g));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Only Golden Weapons can be rerolled.");
            }
        } else {
            return;
        }
    }

    public void merge(MergeHandler m) {
        Weapon weapon;
        Character character;
        Scanner scan = new Scanner(System.in);
        int userInput = -1;
        int weaponIndex1 = 0;
        int weaponIndex2 = 0;
        int weaponIndex3 = 0;
        int characterIndex1 = 0;
        int characterIndex2 = 0;
        int characterIndex3 = 0;

        System.out.println(String.format("%-60s", " ").replace(" ", "-"));
        System.out.println(String.format("%34s", "Merge"));
        System.out.println(String.format("%-60s", " ").replace(" ", "-"));
        System.out.println("[1] Merge Weapons\n[2] Merge Characters");
        System.out.print("Enter Input: ");

        while (userInput < 1 || userInput > 2) {
            while (!scan.hasNextInt()) {
                scan.next();
            }
            userInput = scan.nextInt();
        }

        if (userInput == 1) {
            System.out.println(String.format("%18s%21s%5s%5s%s", "Weapons", "Rarity", "  Level", "  Power", "   Type"));
            printList(1);

            System.out.print("Enter valid weapon index to merge into: ");
            weaponIndex1 = scan.nextInt() - 1;
            System.out.print("Enter valid weapon index: ");
            weaponIndex2 = scan.nextInt() - 1;
            System.out.print("Enter valid weapon index: ");
            weaponIndex3 = scan.nextInt() - 1;


            if (this.weaponList.get(weaponIndex1) != null && this.weaponList.get(weaponIndex2) != null && this.weaponList.get(weaponIndex2) != null &&
                (weaponIndex1 != weaponIndex2 && weaponIndex1 != weaponIndex3 && weaponIndex2 != weaponIndex3) &&
                (weaponIndex1 < weaponList.size() && weaponIndex2 < weaponList.size() && weaponIndex3 < weaponList.size())) {
                boolean isValid = m.isValid(this.weaponList.get(weaponIndex1), this.weaponList.get(weaponIndex2), this.weaponList.get(weaponIndex3));

                if (isValid) {
                    Weapon finalWeapon = m.mergeWeapon(this.weaponList.get(weaponIndex1), this.weaponList.get(weaponIndex2), this.weaponList.get(weaponIndex3));
                    weaponList.set(weaponIndex1, finalWeapon); //merges
                    weapon = weaponList.get(weaponIndex3);
                    weaponList.remove(weaponIndex2);
                    weaponList.remove(weapon); //deletes the weapon index 3
                }
                else {
                    System.out.println("Weapons must have the same name and rarity. Returning to Main Menu.");
                }
            }
            else {
                System.out.println("Invalid input. Returning to Main Menu.");
            }
        }

        else {
            System.out.println(String.format("%18s%21s%5s%5s%s", "Characters", "Rarity", "  Element", "  Level", "   Class"));
            printList(2);

            System.out.print("Enter valid character index to merge into: ");
            characterIndex1 = scan.nextInt() - 1;
            System.out.print("Enter valid character index: ");
            characterIndex2 = scan.nextInt() - 1;
            System.out.print("Enter valid character index: ");
            characterIndex3 = scan.nextInt() - 1;

            if (this.characterList.get(characterIndex1) != null && this.characterList.get(characterIndex2) != null && this.characterList.get(characterIndex3) != null) {
                boolean isValid = m.isValid(this.characterList.get(characterIndex1), this.characterList.get(characterIndex2), this.characterList.get(characterIndex3));

                if (isValid) {
                    Character finalCharacter = m.mergeCharacter(this.characterList.get(characterIndex1), this.characterList.get(characterIndex2), this.characterList.get(characterIndex3));
                    characterList.get(characterIndex2).unequip();
                    characterList.get(characterIndex3).unequip(); //unequipped any equipped weapon
                    characterList.set(characterIndex1, finalCharacter); //merges
                    character = characterList.get(characterIndex3);
                    characterList.remove(characterIndex2);
                    characterList.remove(character); //deletes the character index 3
                }
                else {
                    System.out.println("Characters must have the same name and rarity. Returning to Main Menu.");
                }
            }
            else {
                System.out.println("Invalid input. Returning to Main Menu.");
            }
        }
    }


    public void level(LevelHandler l) {
        Scanner scan = new Scanner(System.in);
        int userInput = -1;
        int weaponIndex = 0;
        int characterIndex = 0;
        int amount = 0;

        System.out.println(String.format("%-60s", " ").replace(" ", "-"));
        System.out.println(String.format("%34s", "Level Up"));
        System.out.println(String.format("%-60s", " ").replace(" ", "-"));
        System.out.println("[1] Level Weapons\n[2] Level Characters\n[3] Hone Weapon");
        System.out.print("Enter Input: ");

        while (userInput < 1 || userInput > 3) {
            while (!scan.hasNextInt()) {
                scan.next();
            }
            userInput = scan.nextInt();
        }

        if (userInput == 1) {
            System.out.println(String.format("%18s%21s%5s%5s%s", "Weapons", "Rarity", "  Level", "  Power", "   Type"));
            printList(1);

            System.out.print("Enter valid weapon index: ");
            weaponIndex = scan.nextInt() - 1;

            if (this.weaponList.get(weaponIndex) == null) {
                System.out.println("Weapon index does not exist. Returning to Main Menu.");
                return;
            }

            System.out.print("Enter amount of resources to consume: ");
            amount = scan.nextInt();

            boolean isValid = l.isValid(this.weaponList.get(weaponIndex)); //checks if weapon can still be levelled i.e. level < 50

            if (isValid) {
                boolean hasAmount = this.resources.useRefina(amount); //secondary check if user has enough resources

                if (hasAmount) {
                    if (weaponList.get(weaponIndex).getLevel() + amount > 50){  //final check during levelling to check if it exceeds max level
                        System.out.println("Invalid input. Returning to Main Menu.");
                        return;
                    }

                    Weapon finalWeapon = l.levelWeapon(this.weaponList.get(weaponIndex), amount);
                    this.weaponList.set(weaponIndex, finalWeapon);
                }
                else {
                    System.out.println("Not enough resources to consume. Returning to Main Menu.");
                }
            }
            else {
                System.out.println("Invalid input. Returning to Main Menu.");
            }
        }

        else if (userInput == 2) {
            System.out.println(String.format("%18s%21s%5s%5s%s", "Characters", "Rarity", "  Element", "  Level", "   Class"));
            printList(2);

            System.out.print("Enter valid weapon index: ");
            characterIndex = scan.nextInt() - 1;

            if (this.characterList.get(characterIndex) == null) {
                System.out.println("Character index does not exist. Returning to Main Menu.");
                return;
            }

            System.out.print("Enter amount of resources to consume: ");
            amount = scan.nextInt();

            boolean isValid = l.isValid(this.characterList.get(characterIndex)); //checks if character can still be levelled i.e. level < 100

            if (isValid) {
                boolean hasAmount = this.resources.useRefina(amount); //secondary check if user has enough resources

                if (hasAmount) {
                    if (characterList.get(characterIndex).getLevel() + amount > 100){  //final check during levelling to check if it exceeds max level
                        System.out.println("Invalid input. Returning to Main Menu.");
                        return;
                    }

                    Character finalCharacter = l.levelCharacter(this.characterList.get(characterIndex), amount);
                    this.characterList.set(characterIndex, finalCharacter);
                }
                else {
                    System.out.println("Not enough resources to consume. Returning to Main Menu.");
                }
            }
            else {
                System.out.println("Invalid input. Returning to Main Menu.");
            }
        }

        else if (userInput == 3) {
            printList(1);

            System.out.print("Enter valid weapon index: ");
            weaponIndex = scan.nextInt() - 1;

            if (this.weaponList.get(weaponIndex) == null) {
                System.out.println("Character index does not exist. Returning to Main Menu.");
                return;
            }

            if (!this.weaponList.get(weaponIndex).getType().equals("Ranged")) {
                System.out.println("Weapon type is not Ranged.");
                return;
            }

            if ((((Ranged) this.weaponList.get(weaponIndex)).getCritDamage()) < 2.00f) {
                if (this.resources.useRefina(10)) {
                    l.hone((Ranged) this.weaponList.get(weaponIndex));
                }
                else {
                    System.out.println("Not enough resources to consume. Returning to Main Menu.");
                }
            }
            else {
                System.out.println("Honing failed! Crit Damage Modifier is already " + (int)(((Ranged) this.weaponList.get(weaponIndex)).getCritDamage() * 100) + "%.");
            }
        }
    }


    public ArrayList<Character> getCharacterList() {
        return this.characterList;
    }

    public void addCharacter(Character c) {
        characterList.add(c);
    }

    public void addCharacter(ArrayList<Character> c) {
        characterList.addAll(c);
    }

    public void addWeapon(Weapon w) {
        weaponList.add(w);
    }

    public void addWeapon(ArrayList<Weapon> w) {
        weaponList.addAll(w);
    }

    public ArrayList<Weapon> getWeaponList() {
        return this.weaponList;
    }

    public void printList(int input) {
        if (input == 1)
            for (int i = 0; i < getWeaponList().size(); i++)
                if (getWeaponList().get(i).getEquippedTo() != null) {
                    if (getWeaponList().get(i) instanceof Magical) {
                        System.out.println(String.format("%-30s%7s%7s%8s%10s(%s)  %s", 
                        "[" + (i + 1) + "] " + getWeaponList().get(i).getName(),
                        getWeaponList().get(i).getRarity(),
                        getWeaponList().get(i).getLevel(),
                        getWeaponList().get(i).getPower(),
                        getWeaponList().get(i).getType(), (((Magical)
                        getWeaponList().get(i)).getAdvancedElem()), "[" +
                        (getCharacterList().indexOf(getWeaponList().get(i).getEquippedTo())
                        + 1) + "] " +
                        getWeaponList().get(i).getEquippedTo().getName()));
                    } else {
                        System.out.println(String.format("%-30s%7s%7s%8s%10s            %s", 
                        "[" + (i + 1) + "] " + getWeaponList().get(i).getName(),
                        getWeaponList().get(i).getRarity(),
                        getWeaponList().get(i).getLevel(),
                        getWeaponList().get(i).getPower(),
                        getWeaponList().get(i).getType(), "[" +
                        (getCharacterList().indexOf(getWeaponList().get(i).getEquippedTo())
                        + 1) + "] " +
                        getWeaponList().get(i).getEquippedTo().getName()));
                    }
                } else {
                    if (getWeaponList().get(i) instanceof Magical) {
                        System.out.println(String.format("%-30s%7s%7s%8s%10s(%s)", 
                        "[" + (i + 1) + "] " + getWeaponList().get(i).getName(),
                        getWeaponList().get(i).getRarity(),
                        getWeaponList().get(i).getLevel(),
                        getWeaponList().get(i).getPower(),
                        getWeaponList().get(i).getType(), (((Magical)
                        (getWeaponList().get(i))).getAdvancedElem())));
                    } else {
                        System.out.println(String.format("%-30s%7s%7s%8s%10s", 
                        "[" + (i + 1) + "] " + getWeaponList().get(i).getName(),
                        getWeaponList().get(i).getRarity(),
                        getWeaponList().get(i).getLevel(),
                        getWeaponList().get(i).getPower(),
                        getWeaponList().get(i).getType()));
                    }
                }
        else if (input == 2)
            for (int i = 0; i < getCharacterList().size(); i++)
                if (getCharacterList().get(i).getWeapon() != null) {
                    System.out.println(String.format("%-30s%7s  %7s%8s%10s     %s", 
                    "[" + (i + 1) + "] " + getCharacterList().get(i).getName(),
                    getCharacterList().get(i).getRarity(),
                    getCharacterList().get(i).getElement(),
                    getCharacterList().get(i).getLevel(),
                    getCharacterList().get(i).getType(), "[" +
                    (getWeaponList().indexOf(getCharacterList().get(i).getWeapon())
                    + 1) + "] " +
                    getCharacterList().get(i).getWeapon().getName()));
                } else {
                    System.out.println(String.format("%-30s%7s  %7s%8s%10s", 
                    "[" + (i + 1) + "] " + getCharacterList().get(i).getName(),
                    getCharacterList().get(i).getRarity(),
                    getCharacterList().get(i).getElement(),
                    getCharacterList().get(i).getLevel(),
                    getCharacterList().get(i).getType()));
                }
    }
}
