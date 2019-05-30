package Dashboard;

import PurchaseOrder.Location;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    AnchorPane Apane;

    @FXML
   void show_entry() throws IOException //Opening Item entry form using button
    {

       AnchorPane pane1= FXMLLoader.load(getClass().getResource("../items/Entry.fxml"));
       Apane.getChildren().clear();
       Apane.getChildren().add(pane1);
    }
    @FXML
   void show_assetList()
    {
        try {


            AnchorPane pane1 = FXMLLoader.load(getClass().getResource("../items/ItemsData.fxml"));
            Apane.getChildren().clear();
            Apane.getChildren().add(pane1);
        }
        catch (Exception e)
        {
            System.out.print(e);
        }

    }
    @FXML
    void show_Distribution() throws IOException //Opening Distribution UI using button
    {

        AnchorPane pane2= FXMLLoader.load(getClass().getResource("../Distribution/Distributiion.fxml"));
        Apane.getChildren().clear();
        Apane.getChildren().add(pane2);

    }
    @FXML
    void show_Supplier() throws IOException //Opening Distribution UI using button
    {
        try {



        AnchorPane pane2= FXMLLoader.load(getClass().getResource("../Supplier/Supplier_details.fxml"));
        Apane.getChildren().clear();
        Apane.getChildren().add(pane2); }
        catch (Exception e)
        {

        }

    }
    @FXML
    void show_PO() throws IOException //Opening purchase order UI using button
    {

        AnchorPane pane2= FXMLLoader.load(getClass().getResource("../PurchaseOrder/Location.fxml"));
        Apane.getChildren().clear();
        Apane.getChildren().add(pane2);

    }
    @FXML
    void show_Employee() throws IOException //Opening purchase show employee
    {

        AnchorPane pane2= FXMLLoader.load(getClass().getResource("../Employee/Employee.fxml"));
        Apane.getChildren().clear();
        Apane.getChildren().add(pane2);

    }
    @FXML
    void showDashboard()  //Opening dashboard form
    {
        try {
        {
            AnchorPane pane2 = FXMLLoader.load(getClass().getResource("../Dashboard/DashboardContents.fxml"));
            Apane.getChildren().clear();
            Apane.getChildren().add(pane2);
        }  }
        catch (Exception e)
        {

        }

    }

    @FXML
    void show_Report()  //Opening dashboard form
    {
        try {
            {
                AnchorPane pane2 = FXMLLoader.load(getClass().getResource("../Reports/Reports/report.fxml"));
                Apane.getChildren().clear();
                Apane.getChildren().add(pane2);
            }  }
        catch (Exception e)
        {

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     showDashboard();
    }
}
