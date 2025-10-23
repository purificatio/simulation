package simulation.render.console;

import simulation.entities.Entity;
import simulation.entities.creatures.Herbivore;

public enum Sprites {
    NOTHING("⬜"),
    HERBIVORE("🐄"),
    PREDATOR("🦁"),
    ROCK("🪨"),
    TREE("🌳"),
    GRASS("🌿");
    private final String value;

    Sprites(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return value;
    }

}
