package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import items.insert_data;

import java.net.URL;
import java.util.ResourceBundle;

public class Main extends Application implements Initializable {

    @Override
    public void start(Stage primaryStage) throws Exception{
        String a="../Dashboard/Dashboard.fxml";
        String b="Dashboard.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(a));
        primaryStage.setFullScreen(false);

        primaryStage.setTitle("TRACKit");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
        insert_data update=new insert_data();
        update.setDays();
        update.depreciationCalculation();
        update.updateDeadstockvalue();   //updates the current value of deadstock to 0
        System.out.println("Initialization");
        System.out.println("Updating depreciation");
        System.out.println("Updating days");
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
