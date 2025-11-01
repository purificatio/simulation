package simulation.actions.turn;

import simulation.entities.Entity;
import simulation.entities.EntityFactory;
import simulation.entities.EntityType;
import simulation.entities.creatures.Creature;
import simulation.map.Cell;
import simulation.map.GameMap;
import simulation.map.GameMapUtils;
import simulation.pathfinding.Bfs;
import simulation.pathfinding.PathFinder;
import java.util.Collections;
import java.util.List;
import java.util.Set;


public class MoveCreaturesAction extends TurnAction{
    public MoveCreaturesAction(GameMap gameMap, EntityFactory entityFactory, GameMapUtils gameMapUtils) {
        super(gameMap, entityFactory, gameMapUtils);
    }

    @Override
    public void execute() {
        List<Entity> entities = gameMapUtils.getAllEntitiesByType(EntityType.CREATURE);
        for(Entity entity : entities){
            if(gameMap.getEntityCellGameMap().containsKey(entity)) {
                Creature creature = (Creature) entity;
                PathFinder pathFinder = new Bfs(creature, gameMap, gameMapUtils);
                Set<Cell> closestTargets = gameMapUtils.getClosestTargets(creature);
                List<Cell> path = getPath(pathFinder, closestTargets);
                if (!path.isEmpty()) {
                    creature.makeMove(path, gameMap);
                }
            }
        }
    }

    private List<Cell> getPath(PathFinder pathFinder, Set<Cell> closestTargets){
        List<Cell> path = Collections.emptyList();
        for(Cell targetCell : closestTargets){
            path = pathFinder.getPath(targetCell);
            if(!path.isEmpty()){
                break;
            }
        }
        return path;
    }
}
