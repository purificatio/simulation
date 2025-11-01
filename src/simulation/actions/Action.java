package simulation.actions;

import simulation.entities.EntityFactory;
import simulation.map.GameMap;


public abstract class Action {
    protected final GameMap gameMap;
    protected final EntityFactory entityFactory;
    public Action(GameMap gameMap, EntityFactory entityFactory){
        this.gameMap = gameMap;
        this.entityFactory = entityFactory;
    }

    public abstract void execute();
}
