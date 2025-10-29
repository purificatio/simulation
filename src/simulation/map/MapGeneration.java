package simulation.map;

import simulation.SimulationInitialization;
import simulation.entities.Entity;
import simulation.entities.EntityFactory;
import java.util.LinkedHashMap;
import java.util.Map;


public class MapGeneration {
    private final EntityFactory entityFactory;
    private final int mapHeight;
    private final int mapWidth;
    private final Map<Cell, Entity> cellEntityMap = new LinkedHashMap<>();
    private final Map<Entity, Cell> entityCellMap = new LinkedHashMap<>();

    public MapGeneration(EntityFactory entityFactory, int mapHeight, int mapWidth) {
        this.entityFactory = entityFactory;
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
    }

    public GameMap generate(){
        for(int row = 1; row <= mapHeight; row++){
            for(int column = 1; column <= mapWidth; column++){
                Cell cell = new Cell(row, column);
                Entity entity = entityFactory.createEntity(SimulationInitialization.DEFAULT_ENTITY_TYPE);
                cellEntityMap.put(cell, entity);
                entityCellMap.put(entity, cell);
            }
        }
        return new GameMap(cellEntityMap, entityCellMap, entityFactory, mapHeight, mapWidth);
    }

    public Map<Entity, Cell> getEntityCellMap() {
        return entityCellMap;
    }

    public Map<Cell, Entity> getCellEntityMap() {
        return cellEntityMap;
    }

}
