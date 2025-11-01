package simulation.entities.creatures;

import simulation.entities.Entity;
import simulation.entities.EntityType;
import simulation.entities.supplies.Supply;
import simulation.map.Cell;
import simulation.map.GameMap;


public class Herbivore extends Creature{
    private static final int DEFAULT_HERBIVORE_HEALTH_POINTS = 4;
    private static final int DEFAULT_HERBIVORE_STEP_LENGTH = 1;

    public Herbivore() {
        this.healthPoints = DEFAULT_HERBIVORE_HEALTH_POINTS;
        this.stepLength = DEFAULT_HERBIVORE_STEP_LENGTH;
        targetEntities.add(EntityType.GRASS);
    }

    @Override
    protected void interactWithTarget(Cell targetCell, GameMap gameMap) {
        Supply target = (Supply) gameMap.getEntity(targetCell);
        Cell herbivoreCell = gameMap.getCell(this);
        gameMap.destroyEntity(target);
        healthPoints++;
        gameMap.moveEntity(herbivoreCell, targetCell);
    }


}
