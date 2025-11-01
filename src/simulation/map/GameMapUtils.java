package simulation.map;

import simulation.entities.Entity;
import simulation.entities.EntityType;
import simulation.entities.creatures.Creature;
import java.util.*;


public class GameMapUtils {
    private final GameMap gameMap;

    public GameMapUtils(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public Set<Cell> getClosestTargets(Creature creature){
        Cell creaturePosition = gameMap.getCell(creature);
        List<Cell> targetsPosition = getEntityPositions(creature.getTargetEntities());
        int creatureRow = creaturePosition.row();
        int creatureColumn = creaturePosition.column();
        Set <Cell> closestTargets = new TreeSet<>(new TreeSet<>((o1, o2) -> {
            int firstDiff = Math.abs(creatureRow - o1.row()) + Math.abs(creatureColumn - o1.column());
            int secondDiff = Math.abs(creatureRow - o2.row()) + Math.abs(creatureColumn - o2.column());
            if(firstDiff > secondDiff){
                return 1;
            }else if(firstDiff == secondDiff){
                return (o1.equals(o2))
                        ? 0
                        : 1;
            } else {
                return -1;
            }
        }));
        closestTargets.addAll(targetsPosition);
        return closestTargets;
    }

    private List<Cell> getEntityPositions(List<EntityType> targetEntities){
        List<Cell> positions = new ArrayList<>();
        for(Map.Entry<Cell, Entity> cellEntityEntry : gameMap.getCellEntityGameMap().entrySet()){
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

    public List<Entity> getAllEntitiesByType(EntityType entityType){
        List<Entity> entities = new ArrayList<>();
        Map<Entity, Cell> entityCellMap = gameMap.getEntityCellGameMap();
        for (Entity entity : entityCellMap.keySet()) {
            if(entityType.getEntityClass().isInstance(entity)){
                entities.add(entity);
            }
        }
        return entities;
    }

    public int getEntityCountByType(EntityType entityType){
        Map<Entity, Cell> entityCellMap = gameMap.getEntityCellGameMap();
        int count = 0;
        for(Entity entity : entityCellMap.keySet()){
            if(entity.getClass() == entityType.getEntityClass()){
                count++;
            }
        }
        return count;
    }

    public List<Cell> getFreeCells(){
        List<Cell> freeCells = new ArrayList<>();
        for(int row = 1; row <= gameMap.getMapHeight(); row++){
            for(int column = 1; column <= gameMap.getMapWidth(); column++){
                Cell cell = new Cell(row, column);
                if(!gameMap.getCellEntityGameMap().containsKey(cell)){
                    freeCells.add(cell);
                }
            }
        }
        return freeCells;
    }
}
