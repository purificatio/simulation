package simulation.entities.creatures;

import simulation.entities.Entity;
import simulation.entities.EntityType;

import java.util.ArrayList;
import java.util.List;

public abstract class Creature extends Entity {
    protected int healthPoints;
    public final List<Class<?>> targetEntities;
    private static final boolean CREATURE_PASSABILITY = false;
    Creature(){
        super(CREATURE_PASSABILITY);
        this.targetEntities = new ArrayList<>();
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
