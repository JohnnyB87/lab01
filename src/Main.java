import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    private int windowWidth = 400;
    private int windowHeight = 350;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Window");
//        primaryStage.setMaxHeight(windowHeight);
//        primaryStage.setMaxWidth(windowWidth);
//        primaryStage.setMinHeight(windowHeight);
//        primaryStage.setMinWidth(windowWidth);
        primaryStage.setResizable(false);

        TabPane tabPane = new TabPane();

        String colour = "#bcbcbc";
        GuessingGameTab guessingGameTab = new GuessingGameTab("Guessing Game","Guess", colour);
        LotteryGameTab lotteryGameTab = new LotteryGameTab("Lottery Game","Submit", colour);
        
        guessingGameTab.setClosable(false);
        lotteryGameTab.setClosable(false);
        
        tabPane.getTabs().add(guessingGameTab);
        tabPane.getTabs().add(lotteryGameTab);
        guessingGameTab.showPrizeTab();

        StackPane layout = new StackPane();
        layout.getChildren().add(tabPane);
        
        Scene scene = new Scene(layout, windowWidth, windowHeight);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

}