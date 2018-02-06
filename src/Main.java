import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import  javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    private int windowWidth = 300;
    private int windowLength = 250;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Window");

        TabPane tabPane = new TabPane();

        Tab guessingGame = GuessingGame.guessingGameTab(windowWidth, windowLength);

        Tab lotteryGame = new Tab();
        lotteryGame.setText("Lottery Game");
        lotteryGame.setContent(new Rectangle(windowWidth,windowLength, Color.LAVENDER));

        Tab prizes = new Tab();
        prizes.setText("Prizes");
        prizes.setContent(new Rectangle(windowWidth,windowLength, Color.BLUE));

        tabPane.getTabs().add(guessingGame);
        tabPane.getTabs().add(lotteryGame);
        tabPane.getTabs().add(prizes);


        StackPane layout = new StackPane();

        layout.getChildren().add(tabPane);


        Scene scene = new Scene(layout, windowWidth, windowLength);

        primaryStage.setScene(scene);
        primaryStage.show();

    }


//    public void guessingGameTab(){
//        Button exit = new Button("Exit");
//    }


}
