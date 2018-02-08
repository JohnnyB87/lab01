import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

import com.sun.glass.events.KeyEvent;

public class GuessingGame {

	private static Label label = new Label();
	private static String guessLabel = "Guesses Left: ";

    private static int guessesLeft = 6;
    private static int num = generateNumber();

    public static Tab guessingGameTab(int windowWidth, int windowLength) {

        Tab tab = new Tab();
        
        Button exit;
        Button reset;
        Button guess;
        TextField txtFld = new TextField();
        

        tab.setText("Guessing Game");
        tab.setContent(new Rectangle(windowWidth, windowLength, null));

        String colour = "#111287";
       

        Pane pane = new Pane();
        pane.setBackground(new Background(new BackgroundFill(Color.web(colour),
                CornerRadii.EMPTY, Insets.EMPTY)));


        exit = new Button("Exit");
        reset = new Button("Reset");
        guess = new Button("Guess");

        txtFld.setLayoutX(75);
        txtFld.setLayoutY(50);
        txtFld.setAlignment(Pos.CENTER);
        
        label.setText(guessLabel + Integer.toString(guessesLeft));
        label.setLayoutX(10);
        label.setLayoutY(10);
        label.setTextFill(Color.web("#ff0000"));
        

        exit.setOnAction(e -> exitGame());
        reset.setOnAction(e -> resetGame(txtFld));
        guess.setOnAction(e -> run(txtFld.getText()));
        
        	
        txtFld.setOnKeyPressed((event) -> { 
        	if(event.getCode() == KeyCode.ENTER) {
        		run(txtFld.getText());
        		} 
        	});
            

        TilePane tileButtons = new TilePane(Orientation.VERTICAL);
        tileButtons.setAlignment(Pos.CENTER);
        //tileButtons.setPadding(new Insets(50, 70, 0, 70));
        tileButtons.setHgap(10.0);
        tileButtons.setVgap(8.0);
        tileButtons.setBackground(new Background(new BackgroundFill
                (Color.web(colour),CornerRadii.EMPTY, Insets.EMPTY)));

        guess.setMinWidth(tileButtons.getPrefWidth());
        reset.setMinWidth(tileButtons.getPrefWidth());
        exit.setMinWidth(tileButtons.getPrefWidth());

        tileButtons.getChildren().addAll(label, txtFld, guess, reset, exit);


        tab.setContent(tileButtons);
        //tab.setContent(label);

        return tab;
    }
    
    public static void resetGame(TextField t) {
    	num = generateNumber();
    	guessesLeft = 6;
    	label.setText(guessLabel + Integer.toString(guessesLeft));
    	t.clear();
    }
    
    public static void exitGame() {
    	Alert alert = new Alert(AlertType.CONFIRMATION,
    			"Are you sure you want to quit?",ButtonType.YES,ButtonType.NO);
    	alert.showAndWait();
    	
    	if(alert.getResult() == ButtonType.YES) {
    		Platform.exit();
    	}
    	else {
    		alert.close();
    	}
    }
    
    public static boolean isNumber(String guess) {
    	try {
    		Integer.parseInt(guess);
    		return true;
    		
    	}catch(NumberFormatException nfe){
    		
    		Alert alert = new Alert(Alert.AlertType.ERROR
    				,"Enter a valid number.", ButtonType.OK);
    		alert.showAndWait();
    		return false;
    	}
    }
    
    public static void run(String strGuess) {
    	
    	System.out.println("Number: " + num);
    	System.out.println("Lives Left: " + guessesLeft);
    	
    	boolean isInt = isNumber(strGuess);
    	if(isInt) {
    		int guess = Integer.parseInt(strGuess);
    		checkGuess(guess);
    		
    	}
    }

    public static void checkGuess(int guess){
        boolean winner = num == guess;
        if(winner) {
        	prizes();
        }
        else {
        	higherOrLower(guess);
        	guessesLeft--;
        	label.setText(guessLabel + Integer.toString(guessesLeft));
        	Alert alert = new Alert(AlertType.INFORMATION,
    				"You have " + guessesLeft + " guesses Left.", ButtonType.OK);
        	alert.showAndWait();
        }
        
    }

    public static void higherOrLower(int guess) {
    	Alert alert = new Alert(AlertType.INFORMATION, "", ButtonType.OK);
    	
    	if(guess > num)
    		alert.setContentText("You guessed too high.");
    	else
    		alert.setContentText("You guessed too low.");
    	
    	alert.showAndWait();
    }
    
    public static void prizes() {
    	int num = guessesLeft > 4 ? 4 : guessesLeft;
    	Alert alert = new Alert(AlertType.CONFIRMATION,
				"You won a " + num + " star prize.", ButtonType.OK);
    	alert.showAndWait();
    }

    public static int generateNumber(){
        return new Random().nextInt(100)+1;
    }

}