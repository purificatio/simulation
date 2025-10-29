package simulation;

import simulation.entities.EntityFactory;
import simulation.entities.EntityType;
import simulation.input.UserInput;
import simulation.map.GameMap;
import simulation.map.MapGeneration;
import simulation.output.GameOutput;
import java.io.IOException;


public class SimulationInitialization {
    private final EntityFactory entityFactory = new EntityFactory();
    private final GameOutput gameOutput;
    private final UserInput userInput;
    public static final EntityType DEFAULT_ENTITY_TYPE = EntityType.NOTHING;

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
                MapGeneration mapGeneration = new MapGeneration(entityFactory, rows, columns);
                GameMap gameMap = mapGeneration.generate();
                return new Simulation(gameOutput, userInput, gameMap, entityFactory);
            } catch (IOException e) {
                gameOutput.showMessage(GameOutput.INCORRECT_USER_INPUT_MESSAGE);
            }
        }
    }
}
