package simulation.pathfinding;

import simulation.entities.Entity;
import simulation.entities.creatures.Creature;
import simulation.map.Cell;
import simulation.map.GameMap;
import simulation.map.GameMapUtils;

import java.util.*;


public class Bfs implements PathFinder {
    private final Creature creature;
    private final GameMap gameMap;
    private final Cell creaturePosition;
    private final GameMapUtils gameMapUtils;

    public Bfs(Creature creature, GameMap gameMap, GameMapUtils gameMapUtils) {
        this.creature = creature;
        this.gameMap = gameMap;
        this.gameMapUtils = gameMapUtils;
        this.creaturePosition = gameMap.getCell(creature);
    }

    @Override
    public List<Cell> getPath(Cell target) {
        Queue<Cell> cellQueue = new LinkedList<>();
        Cell current = creaturePosition;
        Set<Cell> visited = new HashSet<>();
        Map<Cell, Cell> cellParent = new HashMap<>();
        cellQueue.add(current);
        cellParent.put(current, null);
        while(!cellQueue.isEmpty()){
            current = cellQueue.poll();
            if(current.equals(target)){
                return reconstructPath(cellParent, current);
            }
            visited.add(current);
            List<Cell> neighbors = gameMap.getNeighbors(current);
            for(Cell neighbor : neighbors){
                if(!visited.contains(neighbor) && (!gameMap.getCellEntityGameMap().containsKey(neighbor) || neighbor.equals(target))){
                    cellQueue.add(neighbor);
                    visited.add(neighbor);
                    cellParent.put(neighbor, current);
                }
            }
        }
        return Collections.emptyList();
    }

    private List<Cell> reconstructPath(Map<Cell, Cell> cellParent, Cell target){
        Cell head = target;
        List<Cell> path = new LinkedList<>();
        while(head != creaturePosition){
            Cell parent = cellParent.get(head);
            path.add(head);
            head = parent;
        }
        return path.reversed();
    }
}
