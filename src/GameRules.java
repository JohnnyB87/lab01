
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public interface GameRules {

    void run(String s);

    void resetGame(Node... n);

    void exitGame();

    void winner();

    void loser();

    void prizes();
    
    boolean isNumber(String s);
}