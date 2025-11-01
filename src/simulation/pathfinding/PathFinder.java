package simulation.pathfinding;

import simulation.map.Cell;

import java.util.List;

public interface PathFinder {
    List<Cell> getPath(Cell target);
}
