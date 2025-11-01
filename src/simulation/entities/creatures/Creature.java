package simulation.entities.creatures;

import simulation.entities.Entity;
import simulation.entities.EntityType;
import simulation.map.Cell;
import simulation.map.GameMap;

import java.util.ArrayList;
import java.util.List;


public abstract class Creature extends Entity {
    private static final boolean CREATURE_PASSABILITY = false;
    protected int stepLength;
    protected int healthPoints;
    protected final List<EntityType> targetEntities;

    Creature(){
        super(CREATURE_PASSABILITY);
        this.targetEntities = new ArrayList<>();
    }

    public void makeMove(List<Cell> path, GameMap gameMap){
        if(path.size() == 1){
            interactWithTarget(path.getFirst(), gameMap);
        }
        walk(path, gameMap);
    }

    protected void walk(List<Cell> path, GameMap gameMap){
        for(int step = 0; step < stepLength; step++){
            if(step + 1 == path.size()){
                return;
            }
            gameMap.moveEntity(gameMap.getCell(this), path.get(step));
        }
    }

    protected abstract void interactWithTarget(Cell targetCell, GameMap gameMap);


    public void takeDamage(int damageAmount, GameMap gameMap){
        if(damageAmount < 0){
            throw new IllegalArgumentException("Damage cannot be less than zero");
        }
        this.healthPoints -= damageAmount;
        if(this.healthPoints <= 0){
            gameMap.destroyEntity(this);
        }
    }

    public List<EntityType> getTargetEntities() {
        return targetEntities;
    }

    public int getHealthPoints() {
        return healthPoints;
    }
}
