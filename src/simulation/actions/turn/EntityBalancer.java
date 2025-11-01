package simulation.actions.turn;

import simulation.entities.Entity;
import simulation.entities.EntityFactory;
import simulation.entities.EntityType;
import simulation.map.Cell;
import simulation.map.GameMap;
import simulation.map.GameMapUtils;

import java.util.*;


public class EntityBalancer extends TurnAction{
    private static final int HERBIVORE_THRESHOLD = 5;
    private static final int GRASS_THRESHOLD = 3;
    private final Map<EntityType, Integer> entityTypesThresholds = new HashMap<>();
    public EntityBalancer(GameMap gameMap, EntityFactory entityFactory, GameMapUtils gameMapUtils) {
        super(gameMap, entityFactory, gameMapUtils);
        entityTypesThresholds.put(EntityType.HERBIVORE, HERBIVORE_THRESHOLD);
        entityTypesThresholds.put(EntityType.GRASS, GRASS_THRESHOLD);
    }

    @Override
    public void execute() {
        for(Map.Entry<EntityType, Integer> entityTypeThreshold : entityTypesThresholds.entrySet()){
            EntityType entityType = entityTypeThreshold.getKey();
            int countOfEntity = gameMapUtils.getEntityCountByType(entityType);
            int threshold = entityTypeThreshold.getValue();
            if(countOfEntity < threshold){
                spawnEntity(entityType, threshold - countOfEntity);
            }
        }
    }

    private void spawnEntity(EntityType entityType, int count){
        List<Cell> freeCells = gameMapUtils.getFreeCells();
        Random random = new Random();
        int iteration = 0;
        while(iteration != count && !freeCells.isEmpty()) {
            int cellIndex = random.nextInt(freeCells.size());
            Cell cell = freeCells.get(cellIndex);
            gameMap.placeEntity(cell, entityFactory.createEntity(entityType));
            freeCells.remove(cellIndex);
            iteration++;
        }
    }
}
