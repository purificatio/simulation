package simulation;

import simulation.entities.EntityFactory;
import simulation.entities.EntityType;
import simulation.input.UserInput;
import simulation.map.MapGeneration;
import simulation.output.GameOutput;
import java.io.IOException;


public class SimulationFactory {
    private final EntityFactory entityFactory = new EntityFactory();
    public static final EntityType DEFAULT_ENTITY_TYPE = EntityType.NOTHING;
    public Simulation create(GameOutput gameOutput, UserInput userInput){
        int columns = 0;
        int rows = 0;
        while(true) {
            try {
                gameOutput.showMessage("Введите желаемое количество колонок: ");
                columns = userInput.getInteger();
                gameOutput.showMessage("Введите желаемое количество строк: ");
                rows = userInput.getInteger();
                MapGeneration mapGeneration = new MapGeneration(entityFactory, rows, columns);

            } catch (IOException e) {
                gameOutput.showMessage(GameOutput.INCORRECT_USER_INPUT_MESSAGE);
            }
        }
    }
}
