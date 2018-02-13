import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class LotteryGameTab extends GameTab implements GameRules{


    public LotteryGameTab(String title, String buttonName){
        super(title, buttonName);

        GridPane pane = new GridPane();

        RadioButton[] rbArray = new RadioButton[49];

        int count = 1;
        int column = 2;
        int row = 2;
        while(count < 50) {
            rbArray[count-1] = new RadioButton(String.format("%02d",count));
            pane.add(rbArray[count-1], column, row);
            column++;
            if(count % 7 == 0){
                row++;
                column = 2;
            }
            count++;
        }

        int padding = 5;
        pane.setHgap(padding);
        pane.setVgap(padding);
        pane.setAlignment(Pos.CENTER);
        pane.setBackground(new Background(new BackgroundFill(Color.web("#00ff00"), CornerRadii.EMPTY, Insets.EMPTY)));

        super.addPane(pane);

        super.getGuess().setDisable(true);
        super.getExit().setOnAction(e -> exitGame());
        super.getReset().setOnAction(e -> resetGame());
        super.getGuess().setOnAction(e -> run(""));
    }

    @Override
    public void run(String s) {
        System.out.println("LOTTERY GAME TEST");
    }

    @Override
    public void checkResult(int i) {

    }

    @Override
    public void resetGame(Node... node) {

    }

    @Override
    public void exitGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to quit?",ButtonType.YES,ButtonType.NO);
        alert.showAndWait();

        if(alert.getResult() == ButtonType.YES) {
            Platform.exit();
        }
        else {
            alert.close();
        }
    }

    @Override
    public void winner() {

    }

    @Override
    public void loser() {

    }

    @Override
    public void prizes() {

    }
}
