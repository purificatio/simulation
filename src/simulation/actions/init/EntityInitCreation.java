package simulation.actions.init;

import simulation.entities.EntityFactory;
import simulation.entities.EntityType;
import simulation.map.Cell;
import simulation.map.GameMap;
import java.util.*;


public class EntityInitCreation extends InitAction {
    private final Map<EntityType, Integer> entityCounts = new LinkedHashMap<>();
    private final Random random = new Random();
    private final List<Cell> cells = new ArrayList<>();

    public EntityInitCreation(GameMap gameMap, EntityFactory entityFactory) {
        super(gameMap, entityFactory);
        entityCounts.put(EntityType.HERBIVORE, getEntityCount(EntityPercent.HERBIVORE.getPercent()));
        entityCounts.put(EntityType.PREDATOR, getEntityCount(EntityPercent.PREDATOR.getPercent()));
        entityCounts.put(EntityType.GRASS, getEntityCount(EntityPercent.GRASS.getPercent()));
        entityCounts.put(EntityType.ROCK, getEntityCount(EntityPercent.ROCK.getPercent()));
        entityCounts.put(EntityType.TREE, getEntityCount(EntityPercent.TREE.getPercent()));
    }

    private int getEntityCount(int percent){
        int count = (int) ((percent / 100.0) * gameMap.getMapSize());
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
        for(Map.Entry<EntityType, Integer> entityTypeCount : entityCounts.entrySet()){
            EntityType entityType = entityTypeCount.getKey();
            int entityCount = entityTypeCount.getValue();
            create(entityType,entityCount);
        }
    }

    private boolean isMapSizeEnough(){
        int entitiesCount = 0;
        for(Integer count : entityCounts.values()){
            entitiesCount += count;
        }
        return entitiesCount <= gameMap.getMapSize();
    }

    private void fillCellsList(List<Cell> cells){
        for(int row = 1; row <= gameMap.getMapHeight(); row++){
            for(int column = 1; column <= gameMap.getMapWidth(); column++){
                Cell cell = new Cell(row, column);
                cells.add(cell);
            }
        }
    }

    private void create(EntityType entityType, int count){
        int iteration = 0;
        while(iteration != count) {
            int cellIndex = random.nextInt(cells.size());
            Cell cell = cells.get(cellIndex);
            gameMap.placeEntity(cell, entityFactory.createEntity(entityType));
            cells.remove(cellIndex);
            iteration++;
        }
    }
}