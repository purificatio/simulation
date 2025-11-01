package simulation.input;

import java.io.IOException;
import java.util.Scanner;


public class ConsoleInput implements UserInput{
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public int getInteger() throws IOException {
        String userInput = scanner.nextLine();
        if(!userInput.matches("^[0-9]+$")){
            throw new IOException("Incorrect input by user, user entered: " + userInput);
        }
        return Integer.parseInt(userInput);
    }

    @Override
    public char getChar() throws IOException {
        String userInput = scanner.nextLine();
        if(!userInput.matches("^[A-Za-z]$")){
            throw new IOException("Incorrect input by user, user entered: " + userInput);
        }
        return userInput.charAt(0);
    }
}
