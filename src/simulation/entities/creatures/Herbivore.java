package simulation.entities.creatures;

import simulation.entities.EntityType;


public class Herbivore extends Creature{
    private static final int DEFAULT_HERBIVORE_HEALTH_POINTS = 4;
    public Herbivore() {
        this.healthPoints = DEFAULT_HERBIVORE_HEALTH_POINTS;
        targetEntities.add(EntityType.GRASS);
    }
}
