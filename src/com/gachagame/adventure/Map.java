package com.gachagame.adventure;

import java.util.ArrayList;

public class Map {
    private String name;
    private int baseAmount;
    private ArrayList<Enemy> enemyList;

    public Map(String name, int baseAmount) {
        this.name = name;
        this.baseAmount = baseAmount;
        this.enemyList = new ArrayList<Enemy>();
    }

    public String getName() {
        return this.name;
    }

    public int getBaseAmount() {
        return this.baseAmount;
    }

    public ArrayList<Enemy> getEnemyList() {
        return this.enemyList;
    }

    public void addEnemy(String name, int power, int quantity) {
        getEnemyList().add(new Enemy(name, power, quantity));
    }
}
