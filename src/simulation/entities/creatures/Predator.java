package simulation.entities.creatures;

public class Predator extends Creature{
    private final int damage;
    public Predator(int healthPoints, int damage) {
        super(healthPoints);
        this.damage = damage;
    }

    public int getDamage(){
        return damage;
    }
}
