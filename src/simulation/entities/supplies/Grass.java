package simulation.entities.supplies;


public class Grass extends Supply{
    private static final int DEFAULT_GRASS_SUPPLY_HEAL = 2;

    public Grass(){
        super();
        this.supplyHeal = DEFAULT_GRASS_SUPPLY_HEAL;
    }
}
