import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class LotteryGameTab extends GameTab {

	private Alert alert;
	private RadioButton[] rbArray = new RadioButton[49];
	private GridPane pane = new GridPane();

	private int[] winningNumbers = generateNumbers();
	private ArrayList<Integer> numbersSelected = new ArrayList<>();
	private int count = 0;
	private boolean sixSelected = false;
	private int matchingNumbers = 0;

    public LotteryGameTab(String title, String buttonName, String colour){
        super(title, buttonName, colour);

        createRadioButtonLayout();

        int padding = 5;
        pane.setHgap(padding);
        pane.setVgap(padding);
        pane.setAlignment(Pos.CENTER);
        pane.setBackground(new Background(new BackgroundFill(Color.web(colour), CornerRadii.EMPTY, Insets.EMPTY)));

        super.addPane(pane);
        super.getGuessButton().setDisable(true);

        super.getResetButton().setOnAction(e -> resetGame());
        super.getGuessButton().setOnAction(e -> guessButtonPressed());
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
    	boolean isSelected = rb.isSelected();
        int size = this.rbArray.length;
    	count = isSelected ? count+1 : count-1;
  
    	if(this.count == 6) {
            int n;
    		this.sixSelected = true;
    		for(int i=0; i<size; i++) {
    			if(!this.rbArray[i].isSelected())
    				this.rbArray[i].setDisable(true);
    			else {
                    n = Integer.parseInt(this.rbArray[i].getText());
                    this.numbersSelected.add(n);
                }
    		}
    		this.getGuessButton().setDisable(false);
    	}
    	else if(this.sixSelected) {
    		for(int i=0; i<size; i++) {
    			this.rbArray[i].setDisable(false);
    		}
    		this.getGuessButton().setDisable(true);
    		this.numbersSelected.clear();
    	}
    }

    private int[] generateNumbers() {
    	int size = 6;
    	int[] numbers = new int[size];
    	int n = new Random().nextInt(49) + 1;
    	boolean isDuplicate;
        numbers[0] = n;

    	for(int i=1; i<size;) {
    		isDuplicate = false;
    		n = new Random().nextInt(49) + 1;
    		for(int j=0; j<i; j++) {
    			if(n == numbers[j]){
                    isDuplicate = true;
                    break;
                }
    		}
    		if(!isDuplicate) {
    			numbers[i] = n;
    			i++;
    		}
    	}

        Arrays.sort(numbers);
    	return numbers;
    }

    private void guessButtonPressed(){
        run("");
//        super.getResetButton().setDisable(true);
//        super.getExitButton().setDisable(true);
    }

    @Override
    public void run(String s) {
        System.out.println("LOTTERY GAME TEST");
        System.out.println("Winning Numbers: " + Arrays.toString(this.winningNumbers));
        System.out.println("Selected Numbers: " + this.numbersSelected);
        super.getGuessButton().setDisable(true);

        int size = this.winningNumbers.length;
        checkResult(size);
        if(this.matchingNumbers > 3)
            winner();
        else
            loser();
        this.matchingNumbers = 0;
    }

    @Override
    public void checkResult(int size) {
        for(int i=0; i<size; i++)
            for(int j=0; j<size; j++)
                if(this.numbersSelected.get(i) == this.winningNumbers[j])
                    this.matchingNumbers++;
    }

    @Override
    public void resetGame() {
        super.resetGame();

    	for(RadioButton r : this.rbArray) {
    		r.setSelected(false);
    		r.setDisable(false);
    	}
    	super.getGuessButton().setDisable(true);
    	this.count = 0;
    	this.winningNumbers = generateNumbers();
    	this.numbersSelected.clear();
    	this.matchingNumbers = 0;
    	super.getPrizeTab().setDisable(true);
    }

    @Override
    public void winner() {
        super.winner();
        String match = Integer.toString(this.matchingNumbers);

        this.alert = new Alert(Alert.AlertType.INFORMATION,
                String.format("CONGRATULATIONS\nYou Win a %s star prize.",match),ButtonType.OK);
        this.alert.showAndWait();

        //super.getPrizeTab().setDisable(false);
        super.getPrizeTab().loadPrizes(this.matchingNumbers);
    }

    @Override
    public void loser() {
    	super.loser();
        String array = Arrays.toString(this.winningNumbers);
        String str = String.format("YOU LOSE%nThe winning numbers were: %s", array);

        this.alert = new Alert(Alert.AlertType.INFORMATION, str, ButtonType.OK);
        this.alert.showAndWait();
    }


}