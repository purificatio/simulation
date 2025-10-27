package simulation;


import simulation.actions.Action;
import simulation.actions.init.EntitySpawner;
import simulation.actions.init.InitAction;
import simulation.actions.turn.TurnAction;
import simulation.entities.EntityFactory;
import simulation.entities.EntityType;
import simulation.input.UserInput;
import simulation.map.GameMap;
import simulation.map.MapGeneration;
import simulation.menu.main.MainMenuHandler;
import simulation.output.GameOutput;
import simulation.render.console.ConsoleRenderer;
import simulation.render.Renderer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Simulation implements MainMenuHandler {
    private final GameMap gameMap;
    private final Renderer renderer;
    private final EntityFactory entityFactory;
    private final List<InitAction> initActions = new ArrayList<>();
    private final List<TurnAction> turnActions = new ArrayList<>();
    private final GameOutput gameOutput;
    private final UserInput userInput;

    public Simulation(GameOutput gameOutput, UserInput userInput, GameMap gameMap) {
        this.gameOutput = gameOutput;
        this.userInput = userInput;
        this.entityFactory = new EntityFactory();;
        this.gameMap = gameMap;
        this.renderer = new ConsoleRenderer();
        initActions.add(new EntitySpawner(gameMap, entityFactory));
        executeActions(initActions);
    }

    @Override
    public void startInfinitySimulation(){
        renderer.render(gameMap);
    }

    public void pauseInfinitySimulation(){

    }

    @Override
    public void nextTurn(){

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
