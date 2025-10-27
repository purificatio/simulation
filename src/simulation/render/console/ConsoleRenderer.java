package simulation.render.console;

import simulation.map.Cell;
import simulation.map.GameMap;
import simulation.Simulation;
import simulation.entities.Entity;
import simulation.render.Renderer;


public class ConsoleRenderer implements Renderer {
    private final GameMap gameMap;
    private final String border;

    public ConsoleRenderer(GameMap gameMap) {
        this.gameMap = gameMap;
        this.border = "=".repeat( gameMap.getMapWidth() * 3);
    };

    @Override
    public void render(GameMap gameMap) {
        StringBuilder mapState = new StringBuilder();
        for(int row = 1; row <= gameMap.getMapHeight(); row++){
            for(int column = 1; column <= gameMap.getMapWidth(); column++){
                Cell cell = new Cell(row, column);
                Entity entity = gameMap.getEntity(cell);
                mapState.append(getSprite(entity));
            }
            mapState.append("\n");
        }
        mapState.append(border);
        System.out.println(mapState);
    }

    public Sprites getSprite(Entity entity) {
        switch (entity.getClass().getSimpleName()){
            case ("Nothing") -> {
                return Sprites.NOTHING;
            }
            case ("Herbivore") -> {
                return Sprites.HERBIVORE;
            }
            case ("Predator") -> {
                return Sprites.PREDATOR;
            }
            case ("Grass") -> {
                return Sprites.GRASS;
            }
            case ("Rock") -> {
                return Sprites.ROCK;
            }
            case ("Tree") -> {
                return Sprites.TREE;
            }
            default -> {
                throw new IllegalArgumentException("Unclassified entity");
            }
        }
    }
}
