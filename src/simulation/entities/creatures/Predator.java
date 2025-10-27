package simulation.entities.creatures;

import simulation.entities.EntityType;


public class Predator extends Creature{
    private static final int DEFAULT_PREDATOR_HEALTH_POINTS = 4;
    private static final int DEFAULT_PREDATOR_DAMAGE = 2;
    private int damage;

    public Predator() {
        this.healthPoints = DEFAULT_PREDATOR_HEALTH_POINTS;
        this.damage = DEFAULT_PREDATOR_DAMAGE;
        targetEntities.add(EntityType.HERBIVORE);
    }

    public int getDamage(){
        return damage;
    }
}
