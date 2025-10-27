package simulation.menu.main;

import simulation.input.UserInput;
import simulation.menu.GameMenu;
import simulation.output.GameOutput;

public class MainMenu extends GameMenu {
    public MainMenu(GameOutput gameOutput, UserInput userInput, MainMenuHandler chooseHandler) {
        super(gameOutput, userInput, chooseHandler);
        addMenuItem("Запустить бесконечный цикл симуляции", chooseHandler::startInfinitySimulation);
        addMenuItem("Выполнить один ход симуляции", chooseHandler::nextTurn);
        addMenuItem("Выполнить определенное количество ходов симуляции", chooseHandler::makeTurns);
    }


}
