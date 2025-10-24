package simulation.map;

import simulation.entities.Entity;

import java.util.Map;

public class GameMap {
    private final Map<Cell, Entity> gameMap;
    public GameMap(Map<Cell, Entity> gameMap){
        this.gameMap = gameMap;
    }

    public void setEntity(int row, int column, Entity entity){
        Cell cell = new Cell(row, column);
        gameMap.put(cell, entity);
    }

    public Entity getEntity(int row, int column){
        Cell cell = new Cell(row, column);
        if(!gameMap.containsKey(cell)){
            throw new IllegalArgumentException("Incorrect cell");
        }
        return gameMap.get(cell);
    }
}
