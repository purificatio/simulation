package simulation.entities.terrainobjects;

import simulation.entities.Entity;

public abstract class TerrainObject extends Entity {
    private static final boolean TERRAIN_OBJECT_PASSABILITY = false;
    protected TerrainObject() {
        super(TERRAIN_OBJECT_PASSABILITY);
    }
}
