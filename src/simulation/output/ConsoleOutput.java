package simulation.output;

public class ConsoleOutput implements GameOutput{
    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
