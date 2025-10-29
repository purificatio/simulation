package simulation.map;

import simulation.SimulationInitialization;
import simulation.entities.Entity;
import simulation.entities.EntityFactory;
import simulation.entities.EntityType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMap {
    private final Map<Cell, Entity> cellEntityGameMap;
    private final Map<Entity, Cell> entityCellGameMap;
    private final EntityFactory entityFactory;
    private final int mapHeight;
    private final int mapWidth;
    public GameMap(Map<Cell, Entity> cellEntityGameMap, Map<Entity, Cell> entityCellGameMap, EntityFactory entityFactory, int mapHeight, int mapWidth){
        this.cellEntityGameMap = cellEntityGameMap;
        this.entityCellGameMap = entityCellGameMap;
        this.entityFactory = entityFactory;
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
    }

    public void placeEntity(Cell cell, Entity entity){
        cellEntityGameMap.put(cell, entity);
        entityCellGameMap.put(entity, cell);
    }

    public Entity getEntity(Cell cell){
        if(!cellEntityGameMap.containsKey(cell)){
            throw new IllegalArgumentException("Incorrect cell");
        }
        return cellEntityGameMap.get(cell);
    }

    public Cell getCell(Entity entity){
        if(!entityCellGameMap.containsKey(entity)){
            throw new IllegalArgumentException("Incorrect entity");
        }
        return entityCellGameMap.get(entity);
    }

    public void moveEntity(Cell from, Cell to){
        if(!getEntity(to).isPassability()){
            throw new IllegalArgumentException("Cell already taken by no passability entity");
        }
        Entity entity = getEntity(from);
        placeEntity(to, entity);
        placeEntity(from, entityFactory.createEntity(SimulationInitialization.DEFAULT_ENTITY_TYPE));
    }

    public List<Cell> getEntityPositions(List<EntityType> targetEntities){
        List<Cell> positions = new ArrayList<>();
        for(Map.Entry<Cell, Entity> cellEntityEntry : cellEntityGameMap.entrySet()){
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
        return mapWidth * mapHeight;
    }

    public Map<Cell, Entity> getCellEntityGameMap(){
        return new HashMap<>(cellEntityGameMap);
    }

    public Map<Entity, Cell> getEntityCellGameMap(){
        return new HashMap<>(entityCellGameMap);
    }
}
