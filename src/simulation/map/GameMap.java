package simulation.map;

import simulation.entities.Entity;
import simulation.entities.EntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameMap {
    private final Map<Cell, Entity> gameMap;
    public GameMap(Map<Cell, Entity> gameMap){
        this.gameMap = gameMap;
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

    public List<Cell> getEntityPositions(List<Class<?>> targetEntities){
        List<Cell> positions = new ArrayList<>();
        for(Map.Entry<Cell, Entity> cellEntityEntry : gameMap.entrySet()){
            Entity entity = cellEntityEntry.getValue();
            for(Class<?> entityType : targetEntities) {
                if (entityType.isInstance(entity)){
                    positions.add(cellEntityEntry.getKey());
                    break;
                }
            }
        }
        return positions;
    }
}
