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

    public List<Cell> getNeighbors(Cell cell){
        List<Cell> neighbors = new ArrayList<>();
        int[][] directions = {
                {0, -1}, // Лево
                {0, 1}, // Право
                {1, 0}, // Вверх
                {-1, 0} // Вниз
        };
        int row = cell.row();
        int column = cell.column();
        for(int[] direction : directions){
            Cell neighbor = new Cell(row + direction[0], column + direction[1]);
            if(cellEntityGameMap.containsKey(neighbor)){
                neighbors.add(neighbor);
            }
        }
        return neighbors;
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
