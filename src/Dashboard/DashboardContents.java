package Dashboard;

import PurchaseOrder.Pcontroller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardContents extends Controller implements Initializable  {

    @FXML
    Label TtlDeadstocklbl;
    @FXML
    Label TtlConsumablelbl;
    @FXML
    Label TtlLocationslbl;
    @FXML
    Label TtlVendorslbl;
    @FXML
    Label TtlEmployeeslbl;
    @FXML
    Label TtlAssignedlbl;
    @FXML
    Label TtlConsumablecost;
    @FXML
    Label TtlDeadstockcost;

   @FXML
   void getDashboardData()
    {
      DashboardDBAccess obj=new DashboardDBAccess();
     TtlDeadstocklbl.setText(obj.getDSqty());
     TtlConsumablelbl.setText(obj.getCSqty());
     TtlAssignedlbl.setText(obj.getAssignedUnit());
     TtlEmployeeslbl.setText(obj.getTotalEmployees());
     TtlLocationslbl.setText(obj.getTotalLocations());
     TtlVendorslbl.setText(obj.getTotalVendors());
     TtlConsumablecost.setText(obj.getTotalConsumableCost());
     TtlDeadstockcost.setText(obj.getTotalDeadstockcost());



    }
     //.select deadstock.t_amount,deadstock.days,dep_rates.rate,dep_rates.asset_name,sum(deadstock.t_amount - deadstock.t_amount*deadstock.days*dep_rates.rate) from deadstock inner join dep_rates on deadstock.de_id=dep_rates.id group by deadstock.d_name;
    @FXML
    void getVendorpane(ActionEvent event) throws Exception{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(
                DashboardContents.class.getResource("../Supplier/Supplier_details.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("ADD");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.show();

    }

    @FXML
    void getDeadstockpane(ActionEvent event) throws Exception{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(
                DashboardContents.class.getResource("../items/Deadstock.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("ADD");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.show();

    }
    @FXML

    void getConsumablepane(ActionEvent event) throws Exception{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(
                DashboardContents.class.getResource("../items/Consumable.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("ADD");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.show();

    }

    @FXML

    void getDistributionpane(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(
                DashboardContents.class.getResource("../Distribution/Distributiion.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("ADD");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.show();
    }

    @FXML
    void getEmployeepane(ActionEvent event) throws Exception{

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(
                DashboardContents.class.getResource("../Employee/Employee.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("ADD");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.show();

    }

    @FXML
    void getLocations(ActionEvent event) throws Exception{

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(
                DashboardContents.class.getResource("../PurchaseOrder/Location.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("ADD");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       getDashboardData();
    }

}
