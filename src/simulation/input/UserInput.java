package simulation.input;

import java.io.IOException;


public interface UserInput {
    int getInteger() throws IOException;
    char getChar() throws IOException;
}
