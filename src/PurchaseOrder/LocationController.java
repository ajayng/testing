package PurchaseOrder;

import Supplier.supplier;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LocationController extends DatabaseAccess implements Initializable {

    @FXML private TableView<Location> tableView;
    @FXML private TableColumn<Location, Integer> IdColumn;

    @FXML private TableColumn<Location, String> NameColumn;
    @FXML private TableColumn<Location, String> CityColumn;
    @FXML private TableColumn<Location, String> StateColumn;
    @FXML private TableColumn<Location, Integer> ZipColumn;
    @FXML private TableColumn<Location, String> PersonColumn;
    @FXML private TableColumn<supplier, Button> deleteColumn;
     @FXML Button BtnSubmit;
    @FXML JFXTextField TFOfficeName;
    @FXML JFXTextField TFEmail;
    @FXML JFXTextField TFZip;
    @FXML JFXComboBox CBState;
    @FXML JFXComboBox CBCity;


    ObservableList <String> State= FXCollections.observableArrayList("Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir","Jharkhand","Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura","Uttar Pradesh","Uttarakhand","West Bengal","Andaman and Nicobar","Chandigarh","Dadra and Nagar Haveli","Daman and Diu","Lakshadweep","Delhi","Puducherry");
    @FXML
    void AddOfficeDetails() {
        try {
            DatabaseAccess Office = new DatabaseAccess();
            Office.addOfficeAddress(TFOfficeName.getText(), TFEmail.getText(), Integer.parseInt(TFZip.getText()), CBState.getSelectionModel().getSelectedItem().toString(), CBCity.getSelectionModel().getSelectedItem().toString());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Data Successfully added", ButtonType.OK);
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all the fields", ButtonType.OK);
            alert.showAndWait();

        }
    }


    @FXML
    void FillCity()
    {
        if(CBState.getSelectionModel().isSelected(0))
        {
            CBCity.getItems().addAll(
                    "Port Blair",
                    "Havelock Island",
                    "Neil Island",
                    "Ross Island",
                    "Diglipur",
                    "Long Island",
                    "Cinque Island",
                    "Mayabunder",
                    "Little Andaman"
            );
        }
        else if(CBState.getSelectionModel().isSelected(1))
        {
            CBCity.getItems().clear();
            CBCity.getItems().addAll("Visakhapatnam",

                    "Tirupati",

                    "Vijayawada",

                    "Puttaparthi",

                    "Khammam",

                    "Kakinada" );

        }
        else if(CBState.getSelectionModel().isSelected(3)) {
            CBCity.getItems().clear();
            CBCity.getItems().addAll("Guwahati",
                                      "Dibrugarh",
                                       "Silchar",
                                        "Jorhat",
                                        " Nagaon",
                                         "Tinsukia",
                                         "Tezpur"


                                     );
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CBState.setItems(State.sorted());
        IdColumn.setCellValueFactory(new PropertyValueFactory<Location, Integer>("EId"));

        // priceColumn.setCellValueFactory(new PropertyValueFactory<Person, Double>("price"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<Location, String>("Officename"));
        StateColumn.setCellValueFactory(new PropertyValueFactory<Location, String>("State"));
        CityColumn.setCellValueFactory(new PropertyValueFactory<Location, String>("City"));
        //stateColumn.setCellValueFactory(new PropertyValueFactory<supplier, String>("State"));
        ZipColumn.setCellValueFactory(new PropertyValueFactory<Location, Integer>("Zip"));
       // deleteColumn.setCellValueFactory( new PropertyValueFactory<supplier,Button>("Action"));
        PersonColumn.setCellValueFactory(new PropertyValueFactory<Location, String>("Contact"));
        // amountColumn.setCellValueFactory(new PropertyValueFactory<Person, Double>("amount"));
        //load dummy data
        tableView.setItems(getPeople());


        //Update the table to allow for the first and last name fields
        //to be editable
        tableView.setEditable(true);
        IdColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        NameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        CityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        StateColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        //  amountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        PersonColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ZipColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

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



    }

   public ObservableList<Location> people;
    /**
     * This method will return an ObservableList of People objects
     */
    public ObservableList<Location>  getPeople()
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
            java.sql.PreparedStatement stmt= conn.prepareStatement("select * from officelocation");
            System.out.print(stmt);
            ResultSet st=stmt.executeQuery();


            while(st.next())
            {

                Integer id = st.getInt("location_id");
                String name = st.getString("officename");
                String contact=st.getString("contact_person");
                String city=st.getString("city");
                String state=st.getString("state");
                Integer pincode=st.getInt("zip");

                //Integer age = rs.getInt("age");
                //String contact_num = rs.getString("contact_num");
                people.add(new Location(id,name,contact,city,state,pincode));
                System.out.println( "Name: "+name+"Phone: " +contact+" "+"City: "+city+"State "+state+"Pincode "+pincode);

            }


            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        return people;

    }
}
