package simulation;

import simulation.menu.main.MainMenu;
import simulation.output.ConsoleOutput;
import simulation.output.GameOutput;
import simulation.input.ConsoleInput;
import simulation.input.UserInput;


public class Main {
    public static void main(String[] args) {
        GameOutput gameOutput = new ConsoleOutput();
        UserInput userInput = new ConsoleInput();
        SimulationInitialization simulationInitialization = new SimulationInitialization(gameOutput, userInput);
        Simulation simulation = simulationInitialization.init();
        simulation.showGameMap();
        MainMenu mainMenu = new MainMenu(gameOutput, userInput, simulation);
        while(true){
            mainMenu.show();
            mainMenu.select();
        }
    }
}
