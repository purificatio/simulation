package simulation.pathfinding;

import simulation.entities.Entity;
import simulation.entities.creatures.Creature;
import simulation.map.Cell;
import simulation.map.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Bfs {
    private final Creature creature;
    private final GameMap gameMap;
    private final Cell creaturePosition;
    private final List<Cell> targetsPosition;

    public Bfs(Creature creature, GameMap gameMap) {
        this.creature = creature;
        this.gameMap = gameMap;
        this.creaturePosition = gameMap.getCell(creature);
        this.targetsPosition = gameMap.getEntityPositions(creature.getTargetEntities());
    }

    public List<Cell> getPath(){
        return targetsPosition;
    }

    public List<Cell> getNeighbors(Cell cell){
        Map<Cell, Entity> cellEntityMap = gameMap.getCellEntityGameMap();
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
            if(cellEntityMap.containsKey(neighbor)){
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    public Cell getClosestTargetCell(){
        int creatureRow = creaturePosition.row();
        int creatureColumn = creaturePosition.column();
        int sum = Integer.MAX_VALUE;
        Cell closestTarget = targetsPosition.getFirst();
        for(Cell targetPosition : targetsPosition){
            int targetRow = targetPosition.row();
            int targetColumn = targetPosition.column();
            int diff = Math.abs(creatureRow - targetRow) + Math.abs(creatureColumn - targetColumn);
            if (diff < sum) {
                sum = diff;
                closestTarget = targetPosition;
            }
        }
        return closestTarget;
    }
}
