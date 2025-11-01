package simulation.entities.creatures;

import simulation.entities.EntityType;
import simulation.map.Cell;
import simulation.map.GameMap;


public class Predator extends Creature{
    private static final int DEFAULT_PREDATOR_HEALTH_POINTS = 4;
    private static final int DEFAULT_PREDATOR_DAMAGE = 2;
    private static final int DEFAULT_PREDATOR_STEP_LENGTH = 2;
    private int damage;

    public Predator() {
        this.healthPoints = DEFAULT_PREDATOR_HEALTH_POINTS;
        this.damage = DEFAULT_PREDATOR_DAMAGE;
        this.stepLength = DEFAULT_PREDATOR_STEP_LENGTH;
        targetEntities.add(EntityType.HERBIVORE);
    }

    @Override
    protected void interactWithTarget(Cell targetCell, GameMap gameMap) {
        Cell predatorCell = gameMap.getCell(this);
        Creature target = (Creature) gameMap.getEntity(targetCell);
        target.takeDamage(damage, gameMap);
        if(target.getHealthPoints() <= 0){
            this.damage++;
            this.healthPoints++;
            gameMap.moveEntity(predatorCell, targetCell);
        }
    }
}
