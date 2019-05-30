package Supplier;
import PurchaseOrder.Person;
import PurchaseOrder.PersonViewController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import  items.insert_data;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class Scontroller extends  insert_data implements Initializable {

    databaseMethod obj1=new databaseMethod();

    @FXML
    JFXTextField phone;
    @FXML
    JFXTextField  supplier_name;
    @FXML
    JFXTextField  house;
    @FXML
    JFXTextField City;
    @FXML
    JFXTextField State;
    @FXML
    JFXTextField Pin;
    @FXML
    TextField supplierSearch;
    @FXML
    JFXCheckBox CitycBox;
    @FXML
    JFXCheckBox PhonecBox;
    @FXML
    JFXCheckBox NamecBox;

    //Saves supplier information

    @FXML
    void getSupplierinfo()
    {
        try {
            String phone_no = phone.getText();
            String Supplier_name = supplier_name.getText();
            String city = City.getText();
            String House = State.getText();
            String state = house.getText();
            Number pincode = Integer.parseInt(Pin.getText());
            // String Address=House+", "+city+", "+state+"-"+pincode;
            insert_data obj1 = new insert_data();
            obj1.Supplier_details(phone_no, Supplier_name.toLowerCase(), city.toLowerCase(), House.toLowerCase(), pincode, state.toLowerCase());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Data Added: \nSupplier name: " + Supplier_name + "\n" + "City: " + city + "\n" + "Phone: " + phone_no, ButtonType.OK);
            alert.showAndWait();
        }
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all the fields", ButtonType.OK);
            alert.showAndWait();
        }
    }
    ////////////////////////////////////////////////////////////////////////////////
    //configure the table
    @FXML private TableView<supplier> tableView;
    @FXML private TableColumn<supplier, String> supplierNameColumn;

    @FXML private TableColumn<supplier, String> additionalColumn;
    @FXML private TableColumn<supplier, String> cityColumn;
    @FXML private TableColumn<supplier, String> stateColumn;
    @FXML private TableColumn<supplier, Integer> pincodeColumn;
    @FXML private TableColumn<supplier, String> phoneColumn;
    @FXML private TableColumn<supplier, Button> deleteColumn;


    //These instance variables are used to create new Person objects
  /*  @FXML private TextField itemNameTextField;
    @FXML private TextField descriptionTextField;
    @FXML private TextField priceTextField;
    @FXML private  TextField unitTextField;
    @FXML private JFXButton detailedPersonViewButton;
*/

    /**
     * This method will allow the user to double click on a cell and update
     * the first name of the person
     */
    public void changesupplierNameCellEvent(TableColumn.CellEditEvent edittedCell)
    {

        supplier supplierSelected =  tableView.getSelectionModel().getSelectedItem();
        supplierSelected.setSupplierName(edittedCell.getNewValue().toString());
        String updatedName=supplierSelected.getSupplierName();
        obj1.update(updatedName,supplierSelected.getPhoneNo(),"supp_name");

    }

    //Changes amount cell value

    public void changephoneCellEvent(TableColumn.CellEditEvent edittedCell)
    {
        supplier supplierSelected =  tableView.getSelectionModel().getSelectedItem();
        BigInteger initialphone=new BigInteger(supplierSelected.getPhoneNo());
        supplierSelected.setPhoneNo(edittedCell.getNewValue().toString());
        BigInteger updatedphone =new BigInteger(supplierSelected.getPhoneNo());
        obj1.update(updatedphone,initialphone,"ph_no"); //change
    }
    /**
     * This method will enable the detailed view button once a row in the table is
     * selected
     */
    /*public void userClickedOnTable()
    {
        this.detailedPersonViewButton.setDisable(false);
    }
    */


    /**
     ADDITIONAL
     */
    public void changeaddressCellEvent(TableColumn.CellEditEvent edittedCell)
    {
        supplier supplierSelected =  tableView.getSelectionModel().getSelectedItem();

        supplierSelected.setAdditonal(edittedCell.getNewValue().toString());
        String updatedAdd=supplierSelected.getAdditional();

        obj1.update(updatedAdd,supplierSelected.getPhoneNo(),"additional");
    }
    /**
      CITY
     */
    public void changecityCellEvent(TableColumn.CellEditEvent edittedCell)
    {
        supplier supplierSelected =  tableView.getSelectionModel().getSelectedItem();
        supplierSelected.setCity(edittedCell.getNewValue().toString());
        String updatedAdd=supplierSelected.getCity();
        obj1.update(updatedAdd,supplierSelected.getPhoneNo(),"city");
    }

    /**
      STATE
     */
    public void changestateCellEvent(TableColumn.CellEditEvent edittedCell)
    {
        supplier supplierSelected =  tableView.getSelectionModel().getSelectedItem();
        supplierSelected.setState(edittedCell.getNewValue().toString());
        String updatedAdd=supplierSelected.getState();
        obj1.update(updatedAdd,supplierSelected.getPhoneNo(),"state");
    }
    /**
     PINCODE
     */
    public void changepincodeCellEvent(TableColumn.CellEditEvent edittedCell)
    {
        supplier supplierSelected =  tableView.getSelectionModel().getSelectedItem();
        Integer pincode=Integer.parseInt(edittedCell.getNewValue().toString());
        supplierSelected.setPincode(pincode);
        String updatedAdd=supplierSelected.getCity();
        obj1.update(updatedAdd,supplierSelected.getPhoneNo(),"pincode");
    }

    @FXML
     void search()
    {
        String field;
        String data;
        if(CitycBox.isSelected()) {
           field="City";
           data=supplierSearch.getText();
        }
        else if(PhonecBox.isSelected())
        {
            field="Ph_no";
            data=supplierSearch.getText();
        }
        else
        {
            field="supp_name";
            data=supplierSearch.getText();
        }
        String name = supplierSearch.getText().toLowerCase();
        tableView.setItems(getPeople("select * from supplier where"+" "+field+ "=" + "'" +data+ "'"));
    }


    /**
     * When this method is called, it will change the Scene to
     * a TableView example
     */
    public void changeScreenButtonPushed(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    /**
     * When this method is called, it will pass the selected Person object to
     * a the detailed view
     */
    /* public void changeSceneToDetailedPersonView(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PersonView.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        //access the controller and call a method
        PersonViewController controller = loader.getController();
        controller.initData(tableView.getSelectionModel().getSelectedItem());

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
  */


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set up the columns in the table
        supplierNameColumn.setCellValueFactory(new PropertyValueFactory<supplier, String>("supplierName"));

       // priceColumn.setCellValueFactory(new PropertyValueFactory<Person, Double>("price"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<supplier, String>("phoneNo"));
        additionalColumn.setCellValueFactory(new PropertyValueFactory<supplier, String>("City"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<supplier, String>("additional"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<supplier, String>("State"));
        pincodeColumn.setCellValueFactory(new PropertyValueFactory<supplier, Integer>("pincode"));
        deleteColumn.setCellValueFactory( new PropertyValueFactory<supplier,Button>("Action"));

       // amountColumn.setCellValueFactory(new PropertyValueFactory<Person, Double>("amount"));
        //load dummy data
        tableView.setItems(getPeople("Select * from supplier"));


        //Update the table to allow for the first and last name fields
        //to be editable
        tableView.setEditable(true);
        supplierNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        additionalColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        cityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        stateColumn.setCellFactory(TextFieldTableCell.forTableColumn());

       // priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
      //  amountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        pincodeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

       /*priceColumn.setCellFactory(cellData -> cellData.getValue.
        priceColumn.setCellFactory(col ->
                new TableCell<Person, Double>() {
                    @Override
                    public void updateItem(Double price, boolean empty) {
                        super.updateItem(price, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(String.format("US$%.2f", price.doubleValue()));
                        }
                    }
                });
           */

        //This will allow the table to select multiple rows at once
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //Disable the detailed person view button until a row is selected
      //  this.detailedPersonViewButton.setDisable(true);
    }


    /**
     * This method will remove the selected row(s) from the table
     */
    public void deleteItemButtonPushed()
    {
        ObservableList<supplier> selectedRows, allPeople;
        allPeople = tableView.getItems();

        //this gives us the rows that were selected
        selectedRows = tableView.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Person objects from the table
        for (supplier person: selectedRows)
        {
            allPeople.remove(person);
        }
    }



    /**
     * This method will create a new Person object and add it to the table
     */
    /*public void newItemButtonPushed()
    {
        Person newPerson = new Person(itemNameTextField.getText(),
                descriptionTextField.getText(),
                Double.parseDouble(priceTextField.getText()),
                Integer.parseInt(unitTextField.getText()),
                cal());



        //Get all the items from the table as a list, then add the new person to
        //the list

        tableView.getItems().add(newPerson);

    } */
   // double cal()
    //{
    //    double p=Double.parseDouble(priceTextField.getText());
    //    int u= Integer.parseInt(unitTextField.getText());
    //    return(p*u);
   // }


    ObservableList<supplier> people;
    /**
     * This method will return an ObservableList of People objects
     */
    public ObservableList<supplier>  getPeople(String query)
    {
        people = FXCollections.observableArrayList();
        try {
            Class.forName("org.postgresql.Driver");
            java.sql.Connection conn= DriverManager.getConnection("jdbc:postgresql://localhost:5433/trackit","postgres", "root");
            if(conn==null) {
                System.out.print("hello");
            }
            else
                System.out.print(conn+"   ");
            java.sql.PreparedStatement stmt= conn.prepareStatement(query);
            System.out.print(stmt);
            ResultSet st=stmt.executeQuery();



            while(st.next())
            {

                String phone = st.getString("ph_no");
                String name = st.getString("supp_name");
               String additional=st.getString("additional");
               String city=st.getString("city");
               String state=st.getString("state");
               Integer pincode=st.getInt("pincode");

                //Integer age = rs.getInt("age");
                //String contact_num = rs.getString("contact_num");
                people.add(new supplier(name,phone,city,additional,state,pincode));
                System.out.println( "Name: "+name+"Phone: " +phone+" Additional: "+additional+"City: "+city+"State "+state+"Pincode "+pincode);

            }


            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        return people;

    }
}
