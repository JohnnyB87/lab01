import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public interface GameRules {

    void run(String s);

    void checkResult(int i);

    void resetGame(TextField t, Label l);

    void exitGame();

    void winner();

    void loser();

    void prizes();
}