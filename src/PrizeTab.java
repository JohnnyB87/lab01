import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class PrizeTab extends Tab{

    private HashMap<String, String> prizes;
    private TilePane tileButtons;
    private Button[] buttons;
    private ListView<String> list;
    private ObservableList<String> items;


    public PrizeTab() {
        this.setText("Prizes");
        this.setClosable(false);

        tileButtons = new TilePane(Orientation.VERTICAL);
        tileButtons.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        tileButtons.setAlignment(Pos.CENTER);
        tileButtons.setHgap(5.0);
        tileButtons.setVgap(10.0);
        tileButtons.setMinHeight(75);

        this.list = new ListView<>();

        //list.setItems(items);

        
        this.list.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    System.out.println("You selected " + newValue);

                    String str = "You won \n" + prizes.get(newValue);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, str);
                    alert.showAndWait();



                    if(newValue.equals(newValue))
                        setDisable(false);
                    else
                        setDisable(true);
                }
        );

        //tileButtons.getChildren().add(list);
        
        this.setContent(this.tileButtons);

    }

    public void loadPrizes(int n){
        try{
            this.prizes = new HashMap<String, String>();
            String pathToFile = "prizes.txt";

            File readMe = new File(pathToFile);
            FileReader fr = new FileReader(readMe);
            BufferedReader br = new BufferedReader(fr);

            String newLine = br.readLine();
            do{
                fillHashMap(n, newLine);
                newLine = br.readLine();
            }while(newLine != null);

            br.close();

        }catch(IOException ioe){

        }

        this.list.setItems(this.items);
        this.tileButtons.getChildren().clear();
        this.tileButtons.getChildren().add(list);

    }

    public void fillHashMap(int n, String newLine){
        this.items = FXCollections.observableArrayList();

        String[] array = newLine.split(",");
        int prizeValue = -1;

        try{
            prizeValue = Integer.parseInt(array[0]);
        }catch(NumberFormatException nfe) {
            System.out.println("ERROR: " + Arrays.toString(array));
        }

        if(prizeValue == n) {
            String key = array[1];
            String value = array[2];
            prizes.put(key, value);
            this.items.add(key);
        }
        else if(prizeValue == -1) {
            System.out.println("ERROR: prizeValue = -1    -     " + array[0]);
        }


    }

}
