package simulation.actions.init;

import simulation.Simulation;
import simulation.entities.EntityFactory;
import simulation.entities.EntityType;
import simulation.map.Cell;
import simulation.map.GameMap;

import java.util.*;

public class EntitySpawner extends InitAction {
    Map<EntityType, Integer> entityCounts = new LinkedHashMap<>();
    private final Random random = new Random();
    List<Cell> cells = new ArrayList<>();

    public EntitySpawner(GameMap gameMap, EntityFactory entityFactory) {
        super(gameMap, entityFactory);
        entityCounts.put(EntityType.HERBIVORE, getEntityCount(EntityPercent.HERBIVORE.getPercent()));
        entityCounts.put(EntityType.PREDATOR, getEntityCount(EntityPercent.PREDATOR.getPercent()));
        entityCounts.put(EntityType.GRASS, getEntityCount(EntityPercent.GRASS.getPercent()));
        entityCounts.put(EntityType.ROCK, getEntityCount(EntityPercent.ROCK.getPercent()));
        entityCounts.put(EntityType.TREE, getEntityCount(EntityPercent.TREE.getPercent()));
    }

    private int getEntityCount(int percent){
        int count = (int) ((percent / 100.0) * Simulation.MAP_SIZE);
        if(count < 1 && percent != 0) {
            count = 1;
        }
        return count;
    }

    @Override
    public void execute() {
        if(!isMapSizeEnough()){
            throw new IllegalStateException("Not enough map size to place entities");
        }
        fillCellsList(cells);
        for(Map.Entry<EntityType, Integer> entityCount : entityCounts.entrySet()){
            spawn(entityCount.getKey(), entityCount.getValue());
        }
    }

    private boolean isMapSizeEnough(){
        int entitiesCount = 0;
        for(Integer count : entityCounts.values()){
            entitiesCount += count;
        }
        return entitiesCount <= Simulation.MAP_SIZE;
    }

    private void fillCellsList(List<Cell> cells){
        for(int row = 1; row <= Simulation.MAP_HEIGHT; row++){
            for(int column = 1; column <= Simulation.MAP_WIDTH; column++){
                Cell cell = new Cell(row, column);
                cells.add(cell);
            }
        }
    }

    private void spawn(EntityType entityType, int count){
        int iteration = 0;
        while(iteration != count) {
            int cellIndex = random.nextInt(cells.size());
            Cell cell = cells.get(cellIndex);
            gameMap.setEntity(cell, entityFactory.createEntity(entityType));
            cells.remove(cellIndex);
            iteration++;
        }
    }
}
