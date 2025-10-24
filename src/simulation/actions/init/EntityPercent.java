package simulation.actions.init;

public enum EntityPercent {
    HERBIVORE(10),
    PREDATOR(5),
    GRASS(10),
    ROCK(5),
    TREE(5);

    private final int percent;
    EntityPercent(int percent){
        this.percent = percent;
    }

    public int getPercent(){
        return percent;
    }
}
