package com.gachagame.player;

public class Resource {
    private int anima;
    private int refina;

    public Resource(int anima, int refina) {
        this.anima = anima;
        this.refina = refina;
    }

    public int getAnima() {
        return this.anima;
    }

    public void setAnima(int amount) {
        this.anima = amount;
    }

    public int getRefina() {
        return this.refina;
    }

    public void setRefina(int amount) {
        this.refina = amount;
    }

    public void addAnima(int amount) {
        setAnima((int)((this.anima + amount) * 0.7f));
    }

    public void addRefina(int amount) {
        setRefina(this.refina + amount);
    }

    public Boolean useAnima(int amount) {
        if (amount > getAnima()) {
            return false;
        }
        setAnima(getAnima() - amount);
        return true;
    }

    public Boolean useRefina(int amount) {
        if (amount > getRefina()) {
            return false;
        }
        setRefina(getRefina() - amount);
        return true;
    }
}
