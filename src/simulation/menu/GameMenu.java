package simulation.menu;

import simulation.input.UserInput;
import simulation.menu.main.MainMenuHandler;
import simulation.output.GameOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static simulation.output.GameOutput.ASK_FOR_CHOOSE_MESSAGE;
import static simulation.output.GameOutput.INCORRECT_USER_INPUT_MESSAGE;


public abstract class GameMenu {
    protected final GameOutput gameOutput;
    protected final UserInput userInput;
    private final ChooseHandler chooseHandler;
    protected final List<Item> menuItems = new ArrayList<>();
    private static final int START_ID = 1;
    private int lastId = START_ID;

    public GameMenu(GameOutput gameOutput, UserInput userInput, ChooseHandler chooseHandler) {
        this.gameOutput = gameOutput;
        this.userInput = userInput;
        this.chooseHandler = chooseHandler;
    }

    protected void addMenuItem(String text, Action action){
        menuItems.add(new Item(text, action));
    }

    public void show() {
        for(Item menuItem : menuItems){
            String item = menuItem.id + ". " + menuItem.text + "\n";
            gameOutput.showMessage(item);
        }
    }

    public void select(){
        while(true) {
            try {
                gameOutput.showMessage(ASK_FOR_CHOOSE_MESSAGE);
                int userChoose = userInput.getInteger();
                Item item = menuItems.get(userChoose - START_ID);
                item.action.execute();
                break;
            } catch (IOException | IndexOutOfBoundsException e) {
                gameOutput.showMessage(INCORRECT_USER_INPUT_MESSAGE + "\n");
            }
        }
    }

    protected interface Action{
        void execute();
    }

    protected class Item {
        private final int id;
        private final String text;
        private final Action action;

        protected Item(String text, Action action) {
            this.id = lastId++;
            this.text = text;
            this.action = action;
        }
    }
}
