package com.gachagame.weapon;

public class Ranged extends Weapon {
    private float critDamage;

    public Ranged(String name, int rarity, int power, int level, String type) {
        super(name, rarity, power, level, type);
        this.critDamage = 1.00f;
    }

    public float getCritDamage() {
        return this.critDamage;
    }

    public void setCritDamage(float critDamage) {
        this.critDamage = critDamage;
    }
    
    @Override
    public int getPower() {
        return (int) (super.getPower() * getCritDamage());
    }

    public void hone() {
        if (getCritDamage() < 2.00f) {
            setCritDamage(getCritDamage() + 0.2f);
            System.out.println("Honing successful! Critcal Damage modifier is now " + (int)(getCritDamage() * 100) + "%.");
        } else {
            System.out.println("Honing failed! Crit Damage Modifier is already " + (int)(getCritDamage() * 100) + "%.");
        }
    }
}
