package simulation.entities;

public enum EntityType {
    HERBIVORE(false),
    PREDATOR(false),
    ROCK(false),
    TREE(false),
    GRASS(false),
    NOTHING(true);
    private final boolean passability;
    EntityType(boolean passability){
        this.passability = passability;
    }

    public boolean isPassability() {
        return passability;
    }
}
