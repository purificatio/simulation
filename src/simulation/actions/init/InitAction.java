package simulation.actions.init;

import simulation.actions.Action;
import simulation.entities.EntityFactory;
import simulation.map.GameMap;

public abstract class InitAction extends Action {
    public InitAction(GameMap gameMap, EntityFactory entityFactory) {
        super(gameMap, entityFactory);
    }
}
