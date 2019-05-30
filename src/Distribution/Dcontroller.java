package Distribution;

import Dashboard.DashboardContents;
import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.control.behavior.TextBinding;
import com.sun.javafx.scene.control.skin.TextFieldSkin;
import items.insert_data;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import  org.controlsfx.control.*;

import org.controlsfx.control.textfield.TextFields;

import java.awt.*;
import java.math.BigInteger;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

public class Dcontroller  extends insert_data implements Initializable {
    ObservableList<String> TypeList = FXCollections.observableArrayList("Consumables", "Deadstock");
    @FXML
    private JFXComboBox CBIType;
    @FXML
     private JFXTextField Iname;
    @FXML
    private AnchorPane ConsumableAnchor;
    @FXML
    private AnchorPane DeadstockAnchor;
   @FXML
           private JFXTextField TFName;
   @FXML
           private JFXTextField TFUnit;
   @FXML
           private DatePicker DatePicker;
   @FXML
           private JFXComboBox CBName;
   @FXML
           private HBox Hbox;
   @FXML
           private HBox RemainingAmountHbox;
   @FXML
           private TextField Runit;
   @FXML
           private TextField TFID;
   @FXML
           private  Label lblItemId;
   @FXML
           private  Tab deadstockTab;
   @FXML
           private  TabPane tabMaster;

    @FXML private TableView<Distribution> TableviewDeadstock;
    @FXML private TableColumn<Distribution , String> InameColumn ;
    @FXML private TableColumn<Distribution, Integer> QuantityColumn;
    @FXML private TableColumn<Distribution, String> DistributeeColumn;
    @FXML private TableColumn<Distribution, String> DateColumn;
    @FXML private TableColumn<Distribution, Integer> IdColumn;
    @FXML private TableView<Distribution> TableviewConsumable;
    @FXML private TableColumn<Distribution , String> CInameColumn ;
    @FXML private TableColumn<Distribution, Integer> CQuantityColumn;
    @FXML private TableColumn<Distribution, String> CDistributeeColumn;
    @FXML private TableColumn<Distribution, String> CDateColumn;
    @FXML private TableColumn<Distribution, Integer> CIdColumn;
   //Fields for retrieving employee iD from Employee table
    String Type;
    String FieldName;
    String FieldID;
    String ID;
    String EmployeeID;
    int Itemid;
    double amount;
    int unit;
    int dquantity;
    Label IDlbl=new Label();
    public void initialize() {
        //   String[] words={"ajay","aneke","abhi"};
        CBIType.setItems(TypeList);
        DistributionDBAccess employee=new DistributionDBAccess();
        employee.getEmployeeName();
        ObservableList<String> name = FXCollections.observableArrayList();
        name.addAll(employee.getEmployeeArray().getArray());
        int s=employee.getEmployeeArray().getSize();
        CBName.setItems(name);
        CBName.visibleRowCountProperty().setValue(s);
        Runit.setVisible(false);
        lblItemId.setVisible(false);
        TFID.setVisible(false);

        InameColumn.setCellValueFactory(new PropertyValueFactory<Distribution, String>("ItemName"));
        QuantityColumn.setCellValueFactory(new PropertyValueFactory<Distribution, Integer>("Quantity"));
        DistributeeColumn.setCellValueFactory(new PropertyValueFactory<Distribution ,String>("Distributee"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<Distribution ,String>("Issuedate"));
        IdColumn.setCellValueFactory(new PropertyValueFactory<Distribution, Integer>("Id"));
        TableviewDeadstock.setItems(getPeople("select * from dstock_distribution"));
        TableviewDeadstock.setEditable(true);

         InameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
         QuantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
         DistributeeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
         DateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
         IdColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));



        CInameColumn.setCellValueFactory(new PropertyValueFactory<Distribution, String>("CitemName"));
        CQuantityColumn.setCellValueFactory(new PropertyValueFactory<Distribution, Integer>("Cquantity"));
        CDistributeeColumn.setCellValueFactory(new PropertyValueFactory<Distribution ,String>("Cdistributee"));
        CDateColumn.setCellValueFactory(new PropertyValueFactory<Distribution ,String>("Cissuedate"));
        CIdColumn.setCellValueFactory(new PropertyValueFactory<Distribution, Integer>("Cid"));
        TableviewConsumable.setItems(getPeople2("select * from cstock_distribution"));


    }

          void fun () {
            JFXAutoCompletePopup<String> autoCompletePopup = new JFXAutoCompletePopup<>();

            autocomplete(Iname.getText(),FieldName,Type);
            int a=0;
            while(a<getArray1().getSize()) {
                autoCompletePopup.getSuggestions().add(getArray1().get(a));
                a++;
            }
            autoCompletePopup.setSelectionHandler(event -> {
                Iname.setText(event.getObject());
                DistributionDBAccess id=new DistributionDBAccess();
                         Itemid=id.getID(Iname.getText(),Type,FieldName,FieldID);
                        lblItemId.setVisible(true);
                        TFID.setVisible(true);
                        TFID.setText(""+Itemid);
                // you can do other actions here when text completed
            });

            // filtering options
            Iname.textProperty().addListener(observable -> {
                autoCompletePopup.filter(string -> string.toLowerCase().contains(Iname.getText().toLowerCase()));
                if (autoCompletePopup.getFilteredSuggestions().isEmpty() || Iname.getText().isEmpty()) {
                    autoCompletePopup.hide();
                    // if you remove textField.getText.isEmpty() when text field is empty it suggests all options
                    // so you can choose
                } else {
                    autoCompletePopup.show(Iname);
                }
            });
        }

        @FXML
        void getComboBoxSeletedItem()
        {
            if(CBIType.getSelectionModel().isSelected(1))
            {
                FieldName="D_name";
                Type="Deadstock";
                FieldID="D_id";
                fun();

            }
            else
            {
                FieldName="c_name";
                Type="Consumable";
                FieldID="C_id";
                fun();

            }
        }
        @FXML
        void getEmployeeId()
        {
            DistributionDBAccess employeeId=new DistributionDBAccess();
           EmployeeID=employeeId.getEmployeeID(CBName.getSelectionModel().getSelectedItem().toString());
           IDlbl.setText(EmployeeID);
           Hbox.getChildren().add(IDlbl);
           Hbox.setVisible(false);
           System.out.print(ID);


        }
        @FXML
        void updateTable()
        {
            TableviewDeadstock.setItems(getPeople("select * from dstock_distribution order by issue_id"));
            TableviewConsumable.setItems(getPeople2("Select * from cstock_distribution order by issue_id"));

        }

        @FXML
       void getQuantity()
        {
           DistributionDBAccess quantity=new DistributionDBAccess();
            unit=quantity.getQuantity(Iname.getText(),Type,FieldName);

           int dunit=Integer.parseInt(TFUnit.getText());
            amount=quantity.getAmount(Iname.getText(),Type,FieldName)*dunit;
           //Label qtylbl=new Label("Remaining:"+unit);
             // RemainingAmountHbox.getChildren().add(qtylbl);


            System.out.print(unit);

        }

    @FXML
    void getRemainingQuantity()
    {
        try {
            DistributionDBAccess quantity = new DistributionDBAccess();
            unit = quantity.getQuantity(Iname.getText(), Type, FieldName);
            Runit.setText("In stock:" + unit);

            //Label qtylbl=new Label("Remaining:"+unit);
            // RemainingAmountHbox.getChildren().add(qtylbl);
            Runit.setVisible(true);
            Runit.setEditable(false);
        }
        catch(Exception e)
        {

        }

    }


        @FXML
       void putdata() {

            if (unit < 1) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Item quantity is not sufficient to issue", ButtonType.OK);
                alert.showAndWait();

            }
            else if(unit-Integer.parseInt(TFUnit.getText())<0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Issuing item quantity is greater than current stock", ButtonType.OK);
                alert.showAndWait();
            }

            else {

                try {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Data Successfully added", ButtonType.OK);
                    alert.showAndWait();
                    DistributionDBAccess data = new DistributionDBAccess();
                    data.putDistributionDetails(new BigInteger(EmployeeID), Type, Itemid, Integer.parseInt(TFUnit.getText()), DatePicker.getValue().toString(), CBName.getSelectionModel().getSelectedItem().toString(), Iname.getText(), amount);


                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all the field or" + e.getMessage(), ButtonType.OK);
                    alert.showAndWait();

                }


            }
        }


    void getConsumablepane()  {
        Stage stage = new Stage();

        stage.setScene(new Scene(ConsumableAnchor));
        stage.setTitle("ADD");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.getScene().getWindow();
        stage.show();
    }


    ObservableList<Distribution> people;
    /**
     * This method will return an ObservableList of People objects
     */
    public ObservableList<Distribution>  getPeople(String query)
    {
        people = FXCollections.observableArrayList();
        try {
            Class.forName("org.postgresql.Driver");
            java.sql.Connection conn= DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit","postgres", "root");
            java.sql.PreparedStatement stmt= conn.prepareStatement(query);
            System.out.print(stmt);
            ResultSet st=stmt.executeQuery();



            while(st.next())
            {
                DistributionDBAccess obj1=new DistributionDBAccess();
                String name = obj1.getItem_name(st.getInt(1));
                int quantity=st.getInt(3);
                String distributee =obj1.getEmp_name(st.getString(2));
                String Date=st.getString(4);
                int issue_id=st.getInt(5);
                //String name = st.getString(7);
                //int quantity=st.getInt(3);
                //String distributee =st.getString(6);


                //Integer age = rs.getInt("age");
                //String contact_num = rs.getString("contact_num");
                people.add(new Distribution(name,quantity,distributee,Date,issue_id));
                System.out.println( "Name: "+name+quantity+distributee);

            }


            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        return people;

    }



    public ObservableList<Distribution>  getPeople2(String query)
    {
        people = FXCollections.observableArrayList();
        try {
            Class.forName("org.postgresql.Driver");
            java.sql.Connection conn= DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit","postgres", "root");
            java.sql.PreparedStatement stmt= conn.prepareStatement(query);
            System.out.print(stmt);
            ResultSet st=stmt.executeQuery();



            while(st.next())
            {
                DistributionDBAccess obj1=new DistributionDBAccess();
                String name = obj1.getCItem_name(st.getInt(5));
                int quantity=st.getInt(2);
                String distributee =obj1.getCEmp_name(st.getString(1));
                String Date=st.getString(3);
                int issue_id=st.getInt(4);
                //Integer age = rs.getInt("age");
                //String contact_num = rs.getString("contact_num");
                people.add(new Distribution(name,quantity,distributee,Date,issue_id,""));
                System.out.println( "Name: "+name+quantity+distributee);

            }


            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        return people;

    }



        @FXML
        void getConsumablepane(ActionEvent event) throws Exception{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(
                DashboardContents.class.getResource("../Distribution/ConsumableUpdate.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("ADD");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.show();

    }

    @FXML
    public void deleteRecord()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setContentText("Enter Issue ID of deleting record:");
        Optional<String> result = dialog.showAndWait();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Item with id: "+result.get()+ "Successfully deleted", ButtonType.OK);
        DistributionDBAccess d1=new DistributionDBAccess();
        alert.showAndWait();
        int id=Integer.parseInt(result.get());
        d1.deleteRecord2(id);


    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
      initialize();
    }
}







