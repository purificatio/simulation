package simulation.entities.creatures;

import simulation.entities.Entity;

import java.util.List;

public abstract class Creature extends Entity {
    private int healthPoints;
    Creature(int healthPoints){
        this.healthPoints = healthPoints;
    }

    public void makeMove(){

    }

    public void takeDamage(int damageAmount){
        if(damageAmount < 0){
            throw new IllegalArgumentException("Damage cannot be less than zero");
        }
        this.healthPoints-=damageAmount;
    }

}
