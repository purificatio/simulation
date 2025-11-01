package simulation.map;

import simulation.entities.Entity;
import simulation.entities.EntityFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMap {
    private final Map<Cell, Entity> cellEntityGameMap = new HashMap<>();
    private final Map<Entity, Cell> entityCellGameMap = new HashMap<>();
    private final int mapHeight;
    private final int mapWidth;
    public GameMap(int mapHeight, int mapWidth){
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
    }

    public void placeEntity(Cell cell, Entity entity){
        if(cellEntityGameMap.containsKey(cell)){
            throw new IllegalStateException("Cell already taken");
        }
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
        if(!cellEntityGameMap.containsKey(from) || cellEntityGameMap.containsKey(to)){
            throw new IllegalArgumentException("Incorrect entity");
        }
        Entity entity = getEntity(from);
        removeEntity(entity);
        placeEntity(to, entity);
    }

    public void removeEntity(Entity entity){
        Cell entityCell = entityCellGameMap.get(entity);
        cellEntityGameMap.remove(entityCell);
        entityCellGameMap.remove(entity);
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
            if(isAtBorder(neighbor)) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    private boolean isAtBorder(Cell cell){
        if(cell.row() >= 1 && cell.row() <= mapHeight){
            if(cell.column() >= 1 && cell.column() <= mapWidth){
                return true;
            }
        }
        return false;
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
