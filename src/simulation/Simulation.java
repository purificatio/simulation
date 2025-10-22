package simulation;


import simulation.render.ConsoleRenderer;
import simulation.render.Renderer;

public class Simulation {
    private final GameMap gameMap;
    private final Renderer renderer;
    Simulation(){
        MapGeneration mapGeneration = new MapGeneration(10, 5);
        this.gameMap = new GameMap(mapGeneration.generate());
        this.renderer = new ConsoleRenderer();
    }

    private void startSimulation(){

    }
    private void nextTurn(){

    }
    private void pauseSimulation(){

    }
}
