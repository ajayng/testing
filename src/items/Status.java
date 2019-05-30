package items;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;

import java.awt.*;
import java.math.BigDecimal;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.Year;
import java.util.ResourceBundle;

public class Status implements Initializable {

   @FXML
   TextField TFdid;
   @FXML
    TextField TFqty;
   @FXML
    TextField TFAmount;
   @FXML
    ComboBox CBCategory;
   @FXML
    DatePicker Date;

   ObservableList<String> Category= FXCollections.observableArrayList("Written off","Sold out","Transferred");

   @FXML
   void status()
   {
       int did=Integer.parseInt(TFdid.getText());
       int quantity=Integer.parseInt(TFqty.getText());
       String category=CBCategory.getSelectionModel().getSelectedItem().toString();
       BigDecimal amount=new BigDecimal(TFAmount.getText()).setScale(2, BigDecimal.ROUND_CEILING);
       String date=Date.getValue().toString();
       System.out.print(date);
       insert_data obj=new insert_data();
       obj.status(did,quantity,category,amount,date);
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Data Added: \nID: "+did+"\n"+"Quantity: "+quantity+"\n"+"Amount: "+amount , ButtonType.OK);
       alert.showAndWait();
   }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CBCategory.setItems(Category);
    }
}
