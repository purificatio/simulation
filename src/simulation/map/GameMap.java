package simulation.map;

import simulation.Simulation;
import simulation.SimulationFactory;
import simulation.entities.Entity;
import simulation.entities.EntityFactory;
import simulation.entities.EntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameMap {
    private final Map<Cell, Entity> gameMap;
    private final EntityFactory entityFactory;
    private final int mapHeight;
    private final int mapWidth;
    private final int mapSize;
    public GameMap(Map<Cell, Entity> gameMap, EntityFactory entityFactory, int mapHeight, int mapWidth){
        this.gameMap = gameMap;
        this.entityFactory = entityFactory;
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        mapSize = mapWidth * mapHeight;
    }

    public void setEntity(Cell cell, Entity entity){
        gameMap.put(cell, entity);
    }

    public Entity getEntity(Cell cell){
        if(!gameMap.containsKey(cell)){
            throw new IllegalArgumentException("Incorrect cell");
        }
        return gameMap.get(cell);
    }

    public void moveEntity(Cell from, Cell to){
        if(!getEntity(to).isPassability()){
            throw new IllegalArgumentException("Cell already taken by no passability entity");
        }
        Entity entity = getEntity(from);
        setEntity(to, entity);
        setEntity(from, entityFactory.createEntity(SimulationFactory.DEFAULT_ENTITY_TYPE));
    }

    public List<Cell> getEntityPositions(List<EntityType> targetEntities){
        List<Cell> positions = new ArrayList<>();
        for(Map.Entry<Cell, Entity> cellEntityEntry : gameMap.entrySet()){
            Entity entity = cellEntityEntry.getValue();
            for(EntityType entityType : targetEntities) {
                Class<?> entityClass = entityType.getEntityClass();
                if (entityClass.isInstance(entity)){
                    positions.add(cellEntityEntry.getKey());
                    break;
                }
            }
        }
        return positions;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapSize() {
        return mapSize;
    }
}
