package simulation.menu.main;

import simulation.menu.ChooseHandler;

public interface MainMenuHandler extends ChooseHandler {
    void startInfinitySimulation();
    void nextTurn();
    void makeTurns();
}
