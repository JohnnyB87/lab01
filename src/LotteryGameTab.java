import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

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

	private Alert alert;
	private RadioButton[] rbArray = new RadioButton[49];
	private GridPane pane = new GridPane();
	private int[] winningNumbers = generateNumbers();
	private ArrayList<Integer> numbersSelected = new ArrayList<>();
	private int count = 0;
	private boolean sixSelected = false;
	//private ToggleGroup tg = new ToggleGroup();

    public LotteryGameTab(String title, String buttonName){
        super(title, buttonName);

        createRadioButtonLayout();

        //winner();
        //run("");

        int padding = 5;
        pane.setHgap(padding);
        pane.setVgap(padding);
        pane.setAlignment(Pos.CENTER);
        pane.setBackground(new Background(new BackgroundFill(Color.web("#00ff00"), CornerRadii.EMPTY, Insets.EMPTY)));

        super.addPane(pane);

        super.getGuessButton().setDisable(true);
        super.getExitButton().setOnAction(e -> exitGame());
        super.getResetButton().setOnAction(e -> resetGame());
        super.getGuessButton().setOnAction(e -> run(""));
//        for(int i=0; i<this.rbArray.length;i++) {
//        	RadioButton rb = this.rbArray[i];
//        	rb.setOnAction(e -> onRadioButtonClick(rb));
//        }
    }

    private void createRadioButtonLayout() {
    	int count = 1;
        int column = 2;
        int row = 2;
                
        while(count < 50) {
        	
            RadioButton rb = this.rbArray[count-1] = new RadioButton(String.format("%02d",count));
            this.pane.add(rb, column, row);
            column++;
            if(count % 7 == 0){
                row++;
                column = 2;
            }
            count++;
            rb.setOnAction(e -> onRadioButtonClick(rb));
        }
		
	}
    
    private void onRadioButtonClick(RadioButton rb) {
    	//System.out.println(rb.getText());
    	int n = Integer.parseInt(rb.getText());
    	int size = this.numbersSelected.size();
    	this.count = rb.isSelected() ? count+1 : count-1;
    	
  
    	if(this.count == 6) {
    		this.sixSelected = true;
    		for(int i=0; i<this.rbArray.length; i++) {
    			if(!this.rbArray[i].isSelected()) {
    				this.rbArray[i].setDisable(true);
    			}
    			else {
    				this.numbersSelected.add(Integer.parseInt(this.rbArray[i].getText()));
    			}
    		}
    		this.getGuessButton().setDisable(false);
    	}
    	else if(this.sixSelected) {
    		for(int i=0; i<this.rbArray.length; i++) {
    			this.rbArray[i].setDisable(false);
    		}
    		this.getGuessButton().setDisable(true);
    	}
    }

    private int[] generateNumbers() {
    	int size = 6;
    	int[] numbers = new int[size];
    	int n = new Random().nextInt(49) + 1;
    	numbers[0] = n;
    	boolean isDuplicate = false;
    	for(int i=1; i<size;) {
    		isDuplicate = false;
    		n = new Random().nextInt(49) + 1;
    		for(int j=0; j<i; j++) {
    			isDuplicate = n == numbers[j] ? true : false;
    			if(isDuplicate) break;
    		}
    		if(!isDuplicate) {
    			numbers[i] = n;
    			i++;
    		}
    	}
    	return numbers;
    }
    
	@Override
    public void run(String s) {
        System.out.println("LOTTERY GAME TEST");
        System.out.println(Arrays.toString(this.winningNumbers));
    }

    @Override
    public void checkResult(int i) {

    }

    @Override
    public void resetGame(Node... node) {
    	for(RadioButton r : this.rbArray) {
    		r.setSelected(false);
    		r.setDisable(false);
    	}
    	super.getGuessButton().setDisable(true);
    	this.count = 0;
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
    
    public boolean isNumber(String str) {
        try {
        	Integer.parseInt(str);
            return true;
        }catch(NumberFormatException nfe){

            return false;
        }
        
        
    	
    	
    }

    @Override
    public void winner() {
    	this.alert = new Alert(Alert.AlertType.INFORMATION,
                "CONGRATULATIONS\nYou Win a 4 star prize.",ButtonType.OK);
        alert.showAndWait();
        super.getPrizeTab().setDisable(false);
    }

    @Override
    public void loser() {

    }

    @Override
    public void prizes() {

    }
}