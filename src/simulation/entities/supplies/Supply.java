package simulation.entities.supplies;

import simulation.entities.Entity;


public abstract class Supply extends Entity {
    private static final boolean SUPPLY_PASSABILITY = false;
    protected int supplyHeal;

    public Supply() {
        super(SUPPLY_PASSABILITY);
    }
}
