import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class PrizeTab extends Tab{
//    HashMap<String, HashMap<Integer, String>> prizes = new HashMap();
    HashMap<String, String> prizes;


    public PrizeTab() {
        this.setText("Prizes");
        //this.setContent(new Rectangle(windowWidth,windowHeight, Color.BLUE));
       // loadPrizes(5);
    }

    public void loadPrizes(int n){
        try{
            this.prizes = new HashMap();
            File readMe = new File("prizes.txt");
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

    }

    public void showPrizes(){
        String str = "";

        for(HashMap.Entry<String,String> e : this.prizes.entrySet()){
            str += e.getValue() + "\n";
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION, str);
        alert.showAndWait();
    }
}
