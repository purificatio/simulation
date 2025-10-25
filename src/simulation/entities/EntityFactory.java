package simulation.entities;

import simulation.entities.creatures.Herbivore;
import simulation.entities.creatures.Predator;
import simulation.entities.supplies.Grass;
import simulation.entities.terrainobjects.Rock;
import simulation.entities.terrainobjects.Tree;
public class EntityFactory {
    public Entity createEntity(EntityType entityType) {
        switch (entityType) {
            case HERBIVORE -> {
                return new Herbivore();
            }
            case PREDATOR -> {
                return new Predator();
            }
            case GRASS -> {
                return new Grass();
            }
            case ROCK -> {
                return new Rock();
            }
            case TREE -> {
                return new Tree();
            }
            case NOTHING ->
            {
                return new Nothing();
            }
            default -> throw new IllegalArgumentException("Incorrect entity type");
        }
    }
}
