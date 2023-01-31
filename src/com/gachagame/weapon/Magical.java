package com.gachagame.weapon;

public class Magical extends Weapon {
    private String advancedElement;

    public Magical(String name, int rarity, int power, int level, String type, String advancedElement) {
        super(name, rarity, power, level, type);
        this.advancedElement = advancedElement;
    }

    public String getAdvancedElem() {
        return this.advancedElement;
    }

    public void setAdvancedElem(String advancedElement) {
        this.advancedElement = advancedElement;
    }
    
    @Override
    public float getRarityMultiplier() {
        if (getRarity() + 1 == 2) return 0.8f;
        else if (getRarity() + 1 == 3) return 0.9f;
        else if (getRarity() + 1 == 4) return 1.0f;
        else if (getRarity() + 1 == 5) return 1.2f;
        else if (getRarity() + 1 == 6) return 1.5f;

        return 0.7f;
    }

    public int getElementAsInt() {
        String elem = this.advancedElement;

        if (elem.equals("Joker")) return 0;
        else if (elem.equals("Trigger")) return 1;
        else if (elem.equals("Metal")) return 2;
        else if (elem.equals("Cyclone")) return 3;
        else if (elem.equals("Luna")) return 4;
        else if (elem.equals("Heat")) return 5;

        return -1;
    }
}
