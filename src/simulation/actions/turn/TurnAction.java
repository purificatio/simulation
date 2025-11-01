package simulation.actions.turn;

import simulation.actions.Action;
import simulation.entities.EntityFactory;
import simulation.map.GameMap;
import simulation.map.GameMapUtils;

public abstract class TurnAction extends Action {
    protected final GameMapUtils gameMapUtils;
    public TurnAction(GameMap gameMap, EntityFactory entityFactory, GameMapUtils gameMapUtils) {
        super(gameMap, entityFactory);
        this.gameMapUtils = gameMapUtils;
    }
}
