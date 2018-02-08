import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Platform;
import java.util.Random;

public class GuessingGame implements GameRules{

    private int guessesLeft = 6;
    private int num = generateNumber();

    public int getGuessesLeft() {
        return this.guessesLeft;
    }

    public void run(String strGuess) {

        boolean isInt = isNumber(strGuess);
        if(isInt) {
            int guess = Integer.parseInt(strGuess);
            checkResult(guess);

            if(this.guessesLeft == 0) {

                loser();
            }

            System.out.println("Number: " + this.num);
            System.out.println("Lives Left: " + this.guessesLeft);

        }
    }

    public void checkResult(int guess){
        boolean winner = this.num == guess;
        if(winner) {
            winner();
        }
        else {
            higherOrLower(guess);
            this.guessesLeft--;

            Alert alert = new Alert(AlertType.INFORMATION,
                    "You have " + this.guessesLeft + " guesses Left.", ButtonType.OK);
            alert.showAndWait();
        }

    }

    public void resetGame(TextField t, Label l) {
        this.num = generateNumber();
        this.guessesLeft = 6;
        l.setText("Guesses Left: " + Integer.toString(guessesLeft));
        t.clear();
        t.requestFocus();
    }

    public void exitGame() {
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

    public void winner(){}

    public void loser(){
        Alert alert = new Alert(AlertType.INFORMATION,"YOU LOSE",ButtonType.OK);
    }

    public void prizes() {
        int num = this.guessesLeft > 4 ? 4 : this.guessesLeft;
        Alert alert = new Alert(AlertType.CONFIRMATION,
                "You won a " + num + " star prize.", ButtonType.OK);
        alert.showAndWait();
    }

    public boolean isNumber(String guess) {
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

    public void higherOrLower(int guess) {
        Alert alert = new Alert(AlertType.INFORMATION, "", ButtonType.OK);

        if(guess > this.num)
            alert.setContentText("You guessed too high.");
        else
            alert.setContentText("You guessed too low.");

        alert.showAndWait();
    }

    public int generateNumber(){
        return new Random().nextInt(100)+1;
    }


}