import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
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
    private ListView<String> list;
    private ObservableList<String> items;


    public PrizeTab(String colour) {
        this.setText("Prizes");
        this.setClosable(false);

        Label label = new Label("CONGRATULATIONS");
        label.setAlignment(Pos.BASELINE_CENTER);

        this.tileButtons = new TilePane(Orientation.VERTICAL);
        this.tileButtons.setBackground(new Background(new BackgroundFill(Color.web(colour), CornerRadii.EMPTY, Insets.EMPTY)));
        this.tileButtons.setAlignment(Pos.CENTER);
        this.tileButtons.setHgap(5.0);
        this.tileButtons.setVgap(10.0);
        this.tileButtons.setMinHeight(75);
        this.tileButtons.getChildren().add(label);

        this.setContent(this.tileButtons);

    }

    public void loadPrizes(int n){

        this.items = FXCollections.observableArrayList();

        try{
            this.prizes = new HashMap<>();
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

        createListView();

        this.tileButtons.getChildren().clear();
        this.tileButtons.getChildren().add(this.list);


    }

    public void fillHashMap(int n, String newLine){

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
            this.prizes.put(key, value);
            this.items.add(key);
        }
        else if(prizeValue == -1) {
            System.out.println("ERROR: prizeValue = -1    -     " + array[0]);
        }


    }

    private void createListView(){
        this.list = new ListView<>();

        this.list.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    System.out.println("You selected " + newValue);

                    String str = "You won \n" + prizes.get(newValue);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, str);
                    alert.showAndWait();

                    this.setDisable(true);
                }
        );

        this.list.setItems(this.items);
        this.list.getSelectionModel().clearSelection();
        this.list.setPrefHeight((this.items.size() + 2)* 24 + 2);
    }

}
