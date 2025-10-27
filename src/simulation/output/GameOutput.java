package simulation.output;


public interface GameOutput {
    String ASK_FOR_CHOOSE_MESSAGE = "Сделайте свой выбор: ";
    String INCORRECT_USER_INPUT_MESSAGE = "Неверный выбор.";
    void showMessage(String message);
}
