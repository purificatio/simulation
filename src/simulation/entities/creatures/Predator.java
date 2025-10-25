package simulation.entities.creatures;

import simulation.entities.Entity;
import simulation.entities.EntityType;

import java.util.ArrayList;
import java.util.List;

public class Predator extends Creature{
    private static final int DEFAULT_PREDATOR_HEALTH_POINTS = 4;
    private static final int DEFAULT_PREDATOR_DAMAGE = 2;
    private int damage;
    public Predator() {
        this.healthPoints = DEFAULT_PREDATOR_HEALTH_POINTS;
        this.damage = DEFAULT_PREDATOR_DAMAGE;
        targetEntities.add(Herbivore.class);
    }

    public int getDamage(){
        return damage;
    }
}
