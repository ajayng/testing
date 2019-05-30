package PurchaseOrder;

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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author jwright
 */
public class ExampleOfTableViewController implements Initializable {

    //configure the table
    @FXML private TableView<Person> tableView;
    @FXML private TableColumn<Person, String> firstNameColumn;
    @FXML private TableColumn<Person, String> lastNameColumn;
    @FXML private TableColumn<Person, Double> priceColumn;
    @FXML private TableColumn<Person, Integer> unitColumn;
    @FXML private TableColumn<Person, Double> amountColumn;

    
    //These instance variables are used to create new Person objects
    @FXML private TextField itemNameTextField;
    @FXML private TextField descriptionTextField;
    @FXML private TextField priceTextField;
    @FXML private  TextField unitTextField;
    @FXML private JFXButton detailedPersonViewButton;
    
    
    /**
     * This method will allow the user to double click on a cell and update
     * the first name of the person
     */
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
    public void changeSceneToDetailedPersonView(ActionEvent event) throws IOException
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
    
  
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    
    
    
    /**
     * This method will return an ObservableList of People objects
     */
    public ObservableList<Person>  getPeople()
    {
        ObservableList<Person> people = FXCollections.observableArrayList();
        people.add(new Person("Frank","Sinatra",12.00,1,12.00));

        
        return people;
    }
}
