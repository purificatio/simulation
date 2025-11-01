package simulation;


import simulation.actions.Action;
import simulation.actions.init.EntityInitCreation;
import simulation.actions.init.InitAction;
import simulation.actions.turn.EntityBalancer;
import simulation.actions.turn.MoveCreaturesAction;
import simulation.actions.turn.TurnAction;
import simulation.entities.EntityFactory;
import simulation.input.UserInput;
import simulation.map.GameMap;
import simulation.map.GameMapUtils;
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
    private final GameMapUtils gameMapUtils;
    private volatile boolean infinitySimulationFlag = false;


    public Simulation(GameOutput gameOutput, UserInput userInput, GameMap gameMap, EntityFactory entityFactory, GameMapUtils gameMapUtils) {
        this.gameOutput = gameOutput;
        this.userInput = userInput;
        this.entityFactory = entityFactory;
        this.gameMap = gameMap;
        this.renderer = new ConsoleRenderer(gameMap);
        this.gameMapUtils = gameMapUtils;
        initActions.add(new EntityInitCreation(gameMap, entityFactory));
        turnActions.add(new MoveCreaturesAction(gameMap, entityFactory, gameMapUtils));
        turnActions.add(new EntityBalancer(gameMap, entityFactory, gameMapUtils));
        executeActions(initActions);
    }

    @Override
    public void startInfinitySimulation(){
        Thread pauseThread = new Thread(new Pause());
        pauseThread.start();
        infinitySimulationFlag = true;
        while(infinitySimulationFlag){
            executeActions(turnActions);
            showGameMap();
            gameOutput.showMessage("Введите p что бы остановить бесконечную симуляцию: \n");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void pauseInfinitySimulation(){
        infinitySimulationFlag = false;
        gameOutput.showMessage("Бесконечная симуляция остановлена! \n");
    }

    @Override
    public void nextTurn() {
        executeActions(turnActions);
        showGameMap();
    }

    @Override
    public void makeTurns() {
        int turnsCount = 0;
        gameOutput.showMessage("Введите количество ходов: ");
        try {
            turnsCount = userInput.getInteger();
        } catch (IOException e) {
            gameOutput.showMessage(GameOutput.INCORRECT_USER_INPUT_MESSAGE + "\n");
        }
        int iteration = 0;
        while(iteration != turnsCount){
            nextTurn();
            iteration++;
            gameOutput.showMessage("Ход " + iteration + " завершен\n");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void showGameMap(){
        renderer.render(gameMap);
    }

    private void executeActions(List<? extends Action> actionsList){
        for(Action action: actionsList){
            action.execute();
        }
    }

    class Pause implements Runnable{
        @Override
        public void run() {
            while(infinitySimulationFlag){
                try {
                    char userEntered = userInput.getChar();
                    if(!(Character.toLowerCase(userEntered) == 'p')){
                        throw new IOException("Incorrect input");
                    }
                    pauseInfinitySimulation();
                    break;
                } catch (IOException e) {
                    gameOutput.showMessage(GameOutput.INCORRECT_USER_INPUT_MESSAGE + "\n");
                }
            }
        }
    }
}
