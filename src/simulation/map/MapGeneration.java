package simulation.map;

import simulation.Simulation;
import simulation.SimulationFactory;
import simulation.entities.Entity;
import simulation.entities.EntityFactory;
import simulation.entities.EntityType;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapGeneration {
    private final EntityFactory entityFactory;
    private final int mapHeight;
    private final int mapWidth;
    public MapGeneration(EntityFactory entityFactory, int mapHeight, int mapWidth) {
        this.entityFactory = entityFactory;
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
    }

    public GameMap generate(){
        Map<Cell, Entity> map = new LinkedHashMap<>();
        for(int row = 1; row <= mapHeight; row++){
            for(int column = 1; column <= mapWidth; column++){
                Cell cell = new Cell(row, column);
                map.put(cell, entityFactory.createEntity(SimulationFactory.DEFAULT_ENTITY_TYPE));
            }
        }
        return new GameMap(map, entityFactory, mapHeight, mapWidth);
    }

}
