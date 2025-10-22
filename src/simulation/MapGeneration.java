package simulation;

import simulation.entities.Entity;
import simulation.entities.EntityFactory;
import simulation.entities.EntityType;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapGeneration {
    private final int mapWidth;
    private final int mapHeight;

    public MapGeneration(int mapWidth, int mapHeight) {
        this.mapWidth = mapWidth;
        this.mapHeight= mapHeight;
    }
    public Map<Cell, Entity> generate(){
        Map<Cell, Entity> map = new LinkedHashMap<>();
        for(int row = 1; row <= mapWidth; row++){
            for(int column = 1; column <= mapHeight; column++){
                Cell cell = new Cell(row, column);
                map.put(cell, EntityFactory.createEntity(EntityType.NOTHING));
            }
        }
        return map;
    }

}
