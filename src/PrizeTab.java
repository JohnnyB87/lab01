import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class PrizeTab extends Tab{
//    HashMap<String, HashMap<Integer, String>> prizes = new HashMap();
    private HashMap<String, String> prizes;
    private TilePane tileButtons;
    private Button[] buttons;


    public PrizeTab() {
        this.setText("Prizes");

        tileButtons = new TilePane(Orientation.VERTICAL);
        tileButtons.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        tileButtons.setAlignment(Pos.CENTER);
        tileButtons.setHgap(5.0);
        tileButtons.setVgap(8.0);
        tileButtons.setMinHeight(75);


        this.setContent(this.tileButtons);

    }

    public void loadPrizes(int n){
        try{
            this.prizes = new HashMap();
            String pathToFile = "prizes.txt";

            File readMe = new File(pathToFile);
            FileReader fr = new FileReader(readMe);
            BufferedReader br = new BufferedReader(fr);

            String newLine = br.readLine();
            do{
                String[] array = newLine.split(",");
                int prizeValue = Integer.parseInt(array[0]);
                if(prizeValue == n) {
                    String key = array[1];
                    String value = array[2];
                    //prizes.get(prizeValue).put(key,value);
                    prizes.put(key, value);
                }
                newLine = br.readLine();
            }while(newLine != null);

            br.close();

        }catch(IOException ioe){

        }
        buttons = new Button[this.prizes.size()];

        this.tileButtons.getChildren().clear();

    }

    public void showPrizes(){

        int i = 0;

        for(HashMap.Entry<String,String> entry : this.prizes.entrySet()){
            this.buttons[i] = new Button(entry.getKey());
            System.out.println(prizes.get(buttons[i].getText()));
            //str += e.getValue() + "\n";
            this.tileButtons.getChildren().add(this.buttons[i]);
            int j = i;
            this.buttons[i].setOnAction(e -> {
                String str = String.format("You chose the %s prize.%n", prizes.get(this.buttons[j].getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, str);
                alert.showAndWait();
            });

            i++;
        }
    }

}
