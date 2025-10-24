package simulation.actions.turn;

import simulation.actions.Action;
import simulation.entities.EntityFactory;
import simulation.map.GameMap;

public abstract class TurnAction extends Action {
    public TurnAction(GameMap gameMap, EntityFactory entityFactory) {
        super(gameMap, entityFactory);
    }
}
