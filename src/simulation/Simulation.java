package simulation;


import simulation.render.console.ConsoleRenderer;
import simulation.render.Renderer;

public class Simulation {
    private final GameMap gameMap;
    private final Renderer renderer;
    public static final int MAP_WIDTH = 10;
    public static final int MAP_HEIGHT = 10;

    Simulation(){
        MapGeneration mapGeneration = new MapGeneration();
        this.gameMap = new GameMap(mapGeneration.generate());
        this.renderer = new ConsoleRenderer();
    }

    public void startSimulation(){
        renderer.render(gameMap);
    }
    private void nextTurn(){

    }
    private void pauseSimulation(){

    }
}
