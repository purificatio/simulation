package simulation.entities.creatures;

import simulation.entities.Entity;
import simulation.entities.EntityType;
import simulation.entities.supplies.Grass;

import java.util.List;

public class Herbivore extends Creature{
    private static final int DEFAULT_HERBIVORE_HEALTH_POINTS = 4;
    public Herbivore() {
        this.healthPoints = DEFAULT_HERBIVORE_HEALTH_POINTS;
        targetEntities.add(EntityType.GRASS);
    }
}
