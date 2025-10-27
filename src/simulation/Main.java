package simulation;

import simulation.menu.GameMenu;
import simulation.menu.main.MainMenu;
import simulation.output.ConsoleOutput;
import simulation.output.GameOutput;
import simulation.input.ConsoleInput;
import simulation.input.UserInput;


public class Main {
    public static void main(String[] args) {
        GameOutput gameOutput = new ConsoleOutput();
        UserInput userInput = new ConsoleInput();
        SimulationFactory simulationFactory = new SimulationFactory(gameOutput, userInput);
        Simulation simulation = simulationFactory.create();
        MainMenu mainMenu = new MainMenu(gameOutput, userInput, simulation);
        while(true){
            mainMenu.show();
            mainMenu.select();
        }
    }
}
