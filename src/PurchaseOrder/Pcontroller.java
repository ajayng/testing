package PurchaseOrder;

import Supplier.supplier;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.sun.org.apache.xerces.internal.dom.ChildNode;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import com.jfoenix.controls.JFXButton;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;

/**
 * FXML Controller class
 *
 * @author jwright
 */
public class Pcontroller extends DatabaseAccess implements Initializable  {

    //configure the table
    @FXML private TableView<Person> tableView;
    @FXML private TableColumn<Person, String> firstNameColumn;
    @FXML private TableColumn<Person, String> lastNameColumn;
    @FXML private TableColumn<Person, Double> priceColumn;
    @FXML private TableColumn<Person, Integer> unitColumn;
    @FXML private TableColumn<Person, Double> amountColumn;
    ObservableList<String> vendor = FXCollections.observableArrayList();
  /* @FXML
    public void initialize() {
      // DatabaseAccess d1=new DatabaseAccess();
      // d1.getSupplierName();
       vendor.addAll(getSupplierArray().getCompleteArray());
       VendorCB.getItems().addAll(vendor);


    } */

    //These instance variables are used to create new Person objects
    @FXML private TextField itemNameTextField;
    @FXML private TextField descriptionTextField;
    @FXML private TextField priceTextField;
    @FXML private  TextField unitTextField;
    @FXML private JFXButton detailedPersonViewButton;
    @FXML private JFXComboBox VendorCB;
    @FXML private JFXTextArea VendorTA;
    @FXML private JFXTextArea  ShipTA;
    @FXML private  JFXComboBox ShipCB;
    @FXML private  DatePicker Datepicker;

    /**
     * This method will allow the user to double click on a cell and update
     * the first name of the person
     */
    //
    @FXML
    void addShipping(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(
                Pcontroller.class.getResource("Location.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("ADD");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node)event.getSource()).getScene().getWindow() );
        stage.show();

        DatabaseAccess obj=new DatabaseAccess();
        //obj.addShippingAddress(result);

        //System.out.println(result);



    }
    @FXML
    void deleteComboBoxItems()
    {

        String item=ShipCB.getSelectionModel().getSelectedItem().toString();
        System.out.println(item);

       // ShipTA.setText(getShipAddress(s));
    }


    @FXML
     void fillVendorText()
    {

        String s=VendorCB.getValue().toString();

        VendorTA.setText(getSupplierDetails(s));
    }

    public void changeFirstNameCellEvent(CellEditEvent edittedCell)
    {
        Person personSelected =  tableView.getSelectionModel().getSelectedItem();
        personSelected.setFirstName(edittedCell.getNewValue().toString());
    }
    //Changes Price cell value
    public void changePriceNameCellEvent(CellEditEvent edittedCell)
    {
        Person personSelected =  tableView.getSelectionModel().getSelectedItem();
        Double price=Double.parseDouble(edittedCell.getNewValue().toString());
        personSelected.setPrice(price); //change
    }
    //Changes amount cell value
    public void changeAmountCellEvent(CellEditEvent edittedCell)
    {
        Person personSelected =  tableView.getSelectionModel().getSelectedItem();
        Double amount=Double.parseDouble(edittedCell.getNewValue().toString());
        personSelected.setAmount(amount); //change
    }

    public void changeUnitCellEvent(CellEditEvent edittedCell)
    {
        Person personSelected =  tableView.getSelectionModel().getSelectedItem();
        Integer unit=Integer.parseInt(edittedCell.getNewValue().toString());
        personSelected.setUnit(unit); //change
    }
    /**
     * This method will enable the detailed view button once a row in the table is
     * selected
     */
    public void userClickedOnTable()
    {
        this.detailedPersonViewButton.setDisable(false);
    }


    /**
     * This method will allow the user to double click on a cell and update
     * the first name of the person
     */
    public void changeLastNameCellEvent(CellEditEvent edittedCell)
    {
        Person personSelected =  tableView.getSelectionModel().getSelectedItem();
        personSelected.setLastName(edittedCell.getNewValue().toString());
    }

    /**
     * When this method is called, it will change the Scene to
     * a TableView example
     */
    public void changeScreenButtonPushed(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Location.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information

    }

    /**
     * When this method is called, it will pass the selected Person object to
     * a the detailed view
     */
    @FXML
    public void changeSceneToDetailedPersonView(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Location.fxml"));
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

    public void initializeShipComboBox()
    {
        DatabaseAccess shippingDetails=new DatabaseAccess();
        shippingDetails.setShipComboBox();
        ObservableList<String> shipAddress = FXCollections.observableArrayList();
        shipAddress.addAll(shippingDetails.getShipAddressArray().getArray());
        s1=shippingDetails.getShipAddressArray().getSize();
        ShipCB.setItems(shipAddress);
        ShipCB.visibleRowCountProperty().setValue(s1);
    }

    @FXML
    int s;
    int s1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //vendor combobox Initialisation

         DatabaseAccess supplierDetails=new DatabaseAccess();

         supplierDetails.getSupplierName();
        ObservableList<String> vendor = FXCollections.observableArrayList();

        vendor.addAll(supplierDetails.getSupplierArray().getArray());
        s=supplierDetails.getSupplierArray().getSize();

        VendorCB.setItems(vendor);

        VendorCB.visibleRowCountProperty().setValue(s);
         initializeShipComboBox();

        //set up the columns in the table
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Person, Double>("price"));
        unitColumn.setCellValueFactory(new PropertyValueFactory<Person, Integer>("unit"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Person, Double>("amount"));
        //load dummy data
        tableView.setItems(getPeople());

        //Update the table to allow for the first and last name fields
        //to be editable
        tableView.setEditable(true);
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        amountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        unitColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        ObservableList <ContextMenu> items=FXCollections.observableArrayList();
        ShipCB.setEditable(false);
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
        this.detailedPersonViewButton.setDisable(true);
    }


    /**
     * This method will remove the selected row(s) from the table
     */
    public void deleteItemButtonPushed()
    {
        ObservableList<Person> selectedRows, allPeople;
        allPeople = tableView.getItems();

        //this gives us the rows that were selected
        selectedRows = tableView.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Person objects from the table
        for (Person person: selectedRows)
        {
            allPeople.remove(person);
        }
    }



    /**
     * This method will create a new Person object and add it to the table
     */
    public void newItemButtonPushed()
    {
        Person newPerson = new Person(itemNameTextField.getText(),
                descriptionTextField.getText(),
                Double.parseDouble(priceTextField.getText()),
                Integer.parseInt(unitTextField.getText()),
                cal());
                DatabaseAccess obj=new DatabaseAccess();
             //  obj.order_details(itemNameTextField.getText(),descriptionTextField.getText(),unitTextField.getText(),priceTextField.getText(),Datepicker.getDayCellFactory(),V);


        //Get all the items from the table as a list, then add the new person to
        //the list

        tableView.getItems().add(newPerson);

    }
    double cal()
    {
        double p=Double.parseDouble(priceTextField.getText());
        int u= Integer.parseInt(unitTextField.getText());
        return(p*u);
    }

    ObservableList<Person> people;
    /**
     * This method will return an ObservableList of People objects
     */
    public ObservableList<Person>  getPeople()
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
            java.sql.PreparedStatement stmt= conn.prepareStatement("select * from orders");
            System.out.print(stmt);
            ResultSet st=stmt.executeQuery();



            while(st.next())
            {

                String item_name = st.getString("item_name");
                String description = st.getString("description");
                Integer quantity=st.getInt("Orderqty");
                Double price=st.getDouble("price");
                Double amount=st.getDouble("total_amt");


                //Integer age = rs.getInt("age");
                //String contact_num = rs.getString("contact_num");
                people.add(new Person(item_name,description,price,quantity,amount));
                System.out.println( "Name: "+item_name+"Phone: " +description+" Additional: "+quantity+"City: "+amount);

            }


            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        return people;

    }

 /*   ObservableList<Person> people;
    /**
     * This method will return an ObservableList of People objects
     */
   /* public ObservableList<Person>  getPeople()
    {
        people = FXCollections.observableArrayList();

        people.add(new Person("Frank","Sinatra",12.00,1,12.00));


        return people;
    } */

        }
