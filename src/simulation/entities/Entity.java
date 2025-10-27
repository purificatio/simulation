package simulation.entities;


public abstract class Entity {
    private final boolean passability;

    protected Entity(boolean passability) {
        this.passability = passability;
    }

    public boolean isPassability() {
        return passability;
    }
}
