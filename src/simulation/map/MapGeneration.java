package simulation.map;

import simulation.Simulation;
import simulation.entities.Entity;
import simulation.entities.EntityFactory;
import simulation.entities.EntityType;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapGeneration {
    private final EntityFactory entityFactory;
    public MapGeneration(EntityFactory entityFactory) {
        this.entityFactory = entityFactory;
    }

    public Map<Cell, Entity> generate(){
        Map<Cell, Entity> map = new LinkedHashMap<>();
        for(int row = 1; row <= Simulation.MAP_HEIGHT; row++){
            for(int column = 1; column <= Simulation.MAP_WIDTH; column++){
                Cell cell = new Cell(row, column);
                map.put(cell, entityFactory.createEntity(EntityType.NOTHING));
            }
        }
        return map;
    }

}
