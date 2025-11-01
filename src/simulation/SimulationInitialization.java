package simulation;

import simulation.entities.EntityFactory;
import simulation.input.UserInput;
import simulation.map.GameMap;
import simulation.map.GameMapUtils;
import simulation.output.GameOutput;
import java.io.IOException;


public class SimulationInitialization {
    private final EntityFactory entityFactory = new EntityFactory();
    private final GameOutput gameOutput;
    private final UserInput userInput;

    public SimulationInitialization(GameOutput gameOutput, UserInput userInput) {
        this.gameOutput = gameOutput;
        this.userInput = userInput;
    }

    public Simulation create(){
        int columns;
        int rows;
        while(true) {
            try {
                gameOutput.showMessage("Введите желаемое количество колонок: ");
                columns = userInput.getInteger();
                gameOutput.showMessage("Введите желаемое количество строк: ");
                rows = userInput.getInteger();
                GameMap gameMap = new GameMap(rows, columns);
                GameMapUtils gameMapUtils = new GameMapUtils(gameMap);
                return new Simulation(gameOutput, userInput, gameMap, entityFactory, gameMapUtils);
            } catch (IOException e) {
                gameOutput.showMessage(GameOutput.INCORRECT_USER_INPUT_MESSAGE);
            }
        }
    }
}
