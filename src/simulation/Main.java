package simulation;

import simulation.output.ConsoleOutput;
import simulation.output.GameOutput;
import simulation.input.ConsoleInput;
import simulation.input.UserInput;

public class Main {
    public static void main(String[] args) {
        UserInput userInput = new ConsoleInput();
        GameOutput gameOutput = new ConsoleOutput();
        Simulation simulation = new Simulation(userInput, gameOutput);
        simulation.start();
    }
}
