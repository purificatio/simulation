package simulation.entities;

import simulation.entities.creatures.Creature;
import simulation.entities.creatures.Herbivore;
import simulation.entities.creatures.Predator;
import simulation.entities.supplies.Grass;
import simulation.entities.terrainobjects.Rock;
import simulation.entities.terrainobjects.Tree;


public enum EntityType {
    CREATURE(Creature.class),
    HERBIVORE(Herbivore.class),
    PREDATOR(Predator.class),
    ROCK(Rock.class),
    TREE(Tree.class),
    GRASS(Grass.class);

    private final Class<?> entityClass;

    EntityType(Class<?> entityClass) {
        this.entityClass = entityClass;
    }

    public Class<?> getEntityClass() {
        return entityClass;
    }
}
