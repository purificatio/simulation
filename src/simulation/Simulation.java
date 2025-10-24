package simulation;


import simulation.actions.Action;
import simulation.actions.init.EntitySpawner;
import simulation.actions.init.InitAction;
import simulation.actions.turn.TurnAction;
import simulation.entities.EntityFactory;
import simulation.map.GameMap;
import simulation.map.MapGeneration;
import simulation.render.console.ConsoleRenderer;
import simulation.render.Renderer;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    public static final int MAP_WIDTH = 10;
    public static final int MAP_HEIGHT = 10;
    public static final int MAP_SIZE = MAP_WIDTH * MAP_HEIGHT;
    private final GameMap gameMap;
    private final Renderer renderer;
    private final EntityFactory entityFactory;
    public final List<InitAction> initActions = new ArrayList<>();
    public final List<TurnAction> turnActions = new ArrayList<>();

    Simulation(){
        this.entityFactory = new EntityFactory();
        MapGeneration mapGeneration = new MapGeneration(entityFactory);
        this.gameMap = new GameMap(mapGeneration.generate());
        this.renderer = new ConsoleRenderer();
        initActions.add(new EntitySpawner(gameMap, entityFactory));
    }

    public void startSimulation(){
        executeActions(initActions);
        renderer.render(gameMap);
    }

    private void nextTurn(){

    }
    private void pauseSimulation(){

    }

    private void executeActions(List<? extends Action> actionsList){
        for(Action action: actionsList){
            action.execute();
        }
    }
}
