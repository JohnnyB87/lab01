import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    private int windowWidth = 300;
    private int windowHeight = 300;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Window");
        primaryStage.setMaxHeight(windowHeight);
        primaryStage.setMaxWidth(windowWidth);
        primaryStage.setMinHeight(windowHeight);
        primaryStage.setMinWidth(windowWidth);

        TabPane tabPane = new TabPane();

        //Tab guessingGame = GuessingGame.guessingGameTab(windowWidth, windowHeight);

        Tab guessingGameTab = new GuessingGameTab();



        Tab lotteryGame = new Tab();
        lotteryGame.setText("Lottery Game");
        lotteryGame.setContent(new Rectangle(windowWidth,windowHeight, Color.LAVENDER));

        Tab prizes = new Tab();
        prizes.setText("Prizes");
        prizes.setContent(new Rectangle(windowWidth,windowHeight, Color.BLUE));

        tabPane.getTabs().add(guessingGameTab);
        tabPane.getTabs().add(lotteryGame);
        tabPane.getTabs().add(prizes);


        StackPane layout = new StackPane();

        layout.getChildren().add(tabPane);


        Scene scene = new Scene(layout, windowWidth, windowHeight);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

}