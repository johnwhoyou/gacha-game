package com.gachagame.adventure;

public class Enemy {
    private String name;
    private int power;
    private int quantity;

    public Enemy(String name, int power, int quantity) {
        this.name = name;
        this.power = power;
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getTotalPower() {
        return this.power * this.quantity;
    }
}
