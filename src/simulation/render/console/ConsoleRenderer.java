package simulation.render.console;

import simulation.GameMap;
import simulation.Simulation;
import simulation.entities.Entity;
import simulation.render.Renderer;

public class ConsoleRenderer implements Renderer {
    @Override
    public void render(GameMap gameMap) {
        StringBuilder mapState = new StringBuilder();
        for(int row = 1; row <= Simulation.MAP_HEIGHT; row++){
            for(int column = 1; column <= Simulation.MAP_WIDTH; column++){
                Entity entity = gameMap.getEntity(row, column);
                mapState.append(getSprite(entity));
            }
            mapState.append("\n");
        }
        System.out.print(mapState);
    }

    public Sprites getSprite(Entity entity) {
        switch (entity.getClass().getSimpleName()){
            case ("Nothing") -> {
                return Sprites.NOTHING;
            }
            default -> {
                throw new IllegalArgumentException("Unclassified entity");
            }
        }
    }
}
