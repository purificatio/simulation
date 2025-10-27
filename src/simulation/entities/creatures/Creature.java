package simulation.entities.creatures;

import simulation.entities.Entity;
import simulation.entities.EntityType;

import java.util.ArrayList;
import java.util.List;


public abstract class Creature extends Entity {
    private static final boolean CREATURE_PASSABILITY = false;
    protected int healthPoints;
    protected final List<EntityType> targetEntities;

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

    public List<EntityType> getTargetEntities() {
        return targetEntities;
    }
}
