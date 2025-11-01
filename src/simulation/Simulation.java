package simulation;


import simulation.actions.Action;
import simulation.actions.init.EntitySpawner;
import simulation.actions.init.InitAction;
import simulation.actions.turn.TurnAction;
import simulation.entities.Entity;
import simulation.entities.EntityFactory;
import simulation.entities.EntityType;
import simulation.entities.creatures.Creature;
import simulation.input.UserInput;
import simulation.map.Cell;
import simulation.map.GameMap;
import simulation.map.GameMapUtils;
import simulation.map.MapGeneration;
import simulation.menu.main.MainMenuHandler;
import simulation.output.GameOutput;
import simulation.pathfinding.Bfs;
import simulation.pathfinding.PathFinder;
import simulation.render.console.ConsoleRenderer;
import simulation.render.Renderer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class Simulation implements MainMenuHandler {
    private final GameMap gameMap;
    private final Renderer renderer;
    private final EntityFactory entityFactory;
    private final List<InitAction> initActions = new ArrayList<>();
    private final List<TurnAction> turnActions = new ArrayList<>();
    private final GameOutput gameOutput;
    private final UserInput userInput;
    private final GameMapUtils gameMapUtils;

    public Simulation(GameOutput gameOutput, UserInput userInput, GameMap gameMap, EntityFactory entityFactory, GameMapUtils gameMapUtils) {
        this.gameOutput = gameOutput;
        this.userInput = userInput;
        this.entityFactory = entityFactory;
        this.gameMap = gameMap;
        this.renderer = new ConsoleRenderer(gameMap);
        this.gameMapUtils = gameMapUtils;
        initActions.add(new EntitySpawner(gameMap, entityFactory));
        executeActions(initActions);
    }

    @Override
    public void startInfinitySimulation(){
    }

    public void pauseInfinitySimulation(){

    }

    @Override
    public void nextTurn() {
        executeActions(turnActions);
    }

    @Override
    public void makeTurns() {
        int turnsCount = 0;
        gameOutput.showMessage("Введите количество ходов: ");
        try {
            turnsCount = userInput.getInteger();
        } catch (IOException e) {
            gameOutput.showMessage(GameOutput.INCORRECT_USER_INPUT_MESSAGE);
        }
        int iteration = 0;
        while(iteration != turnsCount){
            nextTurn();
            iteration++;
        }
    }

    private void executeActions(List<? extends Action> actionsList){
        for(Action action: actionsList){
            action.execute();
        }
    }
}
