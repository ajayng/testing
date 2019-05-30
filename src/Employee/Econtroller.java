package Employee;

import Supplier.databaseMethod;
import Supplier.supplier;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import PurchaseOrder.Pcontroller;
import javafx.util.converter.IntegerStringConverter;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.plaf.ComboBoxUI;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Vector;

public class Econtroller implements Initializable  {
    //Creating DatabaseAcess object
    DatabaseAccess obj1 = new DatabaseAccess();

    @FXML
    JFXTextField TextFieldName;
    @FXML
    JFXTextField TextFieldPhone;
    @FXML
    JFXTextField TextFieldDesignation;
    @FXML
    JFXComboBox ComboBoxPlace;
    @FXML
    JFXComboBox ComboBoxDepartment;
    @FXML
    JFXCheckBox nameCheckBox;
    @FXML
    JFXCheckBox departmentCheckBox;
    @FXML
    JFXCheckBox placeCheckBox;
    @FXML
    JFXTextField searchTF;

    // Method to add employee details to database


    @FXML
    void addDetails() {
        DatabaseAccess employee = new DatabaseAccess();
        try {
            employee.addDetails(TextFieldName.getText().toLowerCase(), searchDepartmentId(ComboBoxDepartment.getValue().toString()), ComboBoxPlace.getValue().toString(), TextFieldDesignation.getText().toLowerCase(), TextFieldPhone.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Data Added", ButtonType.OK);
            alert.showAndWait();
        }
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    //Method for dialogbox

    @FXML
    void FillplaceCB() {
        DatabaseAccess location=new DatabaseAccess();

        location.getLocations();
        ObservableList<String> locations = FXCollections.observableArrayList();

        locations.addAll(location.getLocationArray().getArray());
        int s=location.getLocationArray().getSize();

        ComboBoxPlace.setItems(locations);

        ComboBoxPlace.visibleRowCountProperty().setValue(s);


    }
    @FXML  void clearall()
    {
         TextFieldName.clear();
         TextFieldPhone.clear();

        TextFieldDesignation.clear();



    }

    //Method for adding department

    @FXML
    void addDepartment() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setContentText("Enter Department place:");
        Optional<String> result = dialog.showAndWait();
        DatabaseAccess dept=new DatabaseAccess();
        dept.addDepartment(result.toString());
    }

    @FXML
    void fillDepartmentCombo() {

        DatabaseAccess DepartmentDetails = new DatabaseAccess();
        DepartmentDetails.getDepartmentName();
        ObservableList<String> Department = FXCollections.observableArrayList();
        Department.addAll(DepartmentDetails.getDepartmentArray().getCompleteArray());

        int size=DepartmentDetails.getDepartmentArray().getSize();

        ComboBoxDepartment.setItems(Department);

        ComboBoxDepartment.visibleRowCountProperty().setValue(size);



    }
    //Method returning dept_id
    Integer searchDepartmentId(String Department)
    {
       DatabaseAccess obj1=new DatabaseAccess();
       int id=obj1.Department_Id(Department);

       return id;
    }

    @FXML
    public void search()
    {
        String field;
        String data;
        if(nameCheckBox.isSelected()) {
            field="emp_name";
            data=searchTF.getText();
        }
        else if(departmentCheckBox.isSelected())
        {
            field="dep_id";
            DatabaseAccess obj=new DatabaseAccess();
            data=obj.getDepartment_id(searchTF.getText());
        }
        else
        {
            field="emp_place";
            data=searchTF.getText();
        }

        tableView.setItems(getPeople("select emp_details.*,department.department_name from emp_details inner  join department on emp_details.emp_dept=department.department_id where"+" "+field+"="+"'"+data+"'"));
    }

    @FXML private TableView<Employee> tableView;
    @FXML private TableColumn<Employee , String> employeeName ;
    @FXML private TableColumn<Employee, String> designation;
    @FXML private TableColumn<Employee, String> department;
    @FXML private TableColumn<Employee, String> place;
    @FXML private TableColumn<Employee, String> mobileNumber;


    //These instance variables are used to create new Person objects
  /*  @FXML private TextField itemNameTextField;
    @FXML private TextField descriptionTextField;
    @FXML private TextField priceTextField;
    @FXML private  TextField unitTextField;
    @FXML private JFXButton detailedPersonViewButton;
*/

    /**
     * This method will allow the user to double click on a cell and update
     * EMPLOYEE NAME
     */
    public void changesemployeeNameCellEvent(TableColumn.CellEditEvent edittedCell)
    {

        Employee employeeSelected =  tableView.getSelectionModel().getSelectedItem();



        int col=tableView.getEditingCell().getColumn();
        int row=tableView.getEditingCell().getRow();
        //System.out.println(tableView.getItems());
        employeeSelected.setEmployeeName(edittedCell.getNewValue().toString());

        String updatedName=employeeSelected.getEmployeeName();

       obj1.update(updatedName,employeeSelected.getMobileNo(),"emp_name");

        //System.out.println(updatedName+"-"+phoneno);


    }

    //DESIGNATION

    public void changedesignationCellEvent(TableColumn.CellEditEvent edittedCell)
    {
        Employee employeeSelected =  tableView.getSelectionModel().getSelectedItem();
        employeeSelected.setDesignation(edittedCell.getNewValue().toString());
        String updatedDesignation=employeeSelected.getDesignation();

        obj1.update(updatedDesignation,employeeSelected.getMobileNo(),"designation");

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
     DEPARTMENT
     */
    public void changedepartmentcellEvent(TableColumn.CellEditEvent edittedCell)
    {
        Employee employeeSelected =  tableView.getSelectionModel().getSelectedItem();
        employeeSelected.setDepartment(edittedCell.getNewValue().toString());
        String updatedDepartment=employeeSelected.getDepartment();

        obj1.update(updatedDepartment,employeeSelected.getMobileNo(),"emp_dept");

    }
    /**
     PLACE
     */
    public void changeplaceCellEvent(TableColumn.CellEditEvent edittedCell)
    {
        Employee employeeSelected =  tableView.getSelectionModel().getSelectedItem();
        employeeSelected.setPlace(edittedCell.getNewValue().toString());
        String updatedplace=employeeSelected.getPlace();

        obj1.update(updatedplace,employeeSelected.getMobileNo(),"emp_place");
    }

    /**
     MOBILENO
     */
    public void changemobilenoCellEvent(TableColumn.CellEditEvent edittedCell)
    {
        Employee employeeSelected =  tableView.getSelectionModel().getSelectedItem();
        BigInteger mobile=new BigInteger(employeeSelected.getMobileNo());
        employeeSelected.setMobileNo(edittedCell.getNewValue().toString());
        BigInteger updatedmobile=new BigInteger(employeeSelected.getMobileNo());

        obj1.update(updatedmobile,mobile,"phone_no");

    }

    @FXML
    void showall()
    {
        tableView.setItems(getPeople("select emp_details.*,department.department_name from emp_details inner  join department on emp_details.emp_dept=department.department_id"));

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
        ComboBoxDepartment.getSelectionModel();
        fillDepartmentCombo();
        FillplaceCB();

        ComboBoxPlace.setEditable(false);

        ComboBoxPlace.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                System.out.println(ov);
                System.out.println(t);
                System.out.println(t1);

            }
        });
        employeeName.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeName"));
        // priceColumn.setCellValueFactory(new PropertyValueFactory<Person, Double>("price"));
        designation.setCellValueFactory(new PropertyValueFactory<Employee,String>("designation"));
        department.setCellValueFactory(new PropertyValueFactory<Employee, String>("department"));
        place.setCellValueFactory(new PropertyValueFactory<Employee, String>("place"));
        mobileNumber.setCellValueFactory(new PropertyValueFactory<Employee, String>("mobileNo"));
       // pincodeColumn.setCellValueFactory(new PropertyValueFactory<supplier, Integer>("pincode"));
        // amountColumn.setCellValueFactory(new PropertyValueFactory<Person, Double>("amount"));
        //load dummy data
        tableView.setItems(getPeople("select emp_details.*,department.department_name from emp_details inner  join department on emp_details.emp_dept=department.department_id"));


        //Update the table to allow for the first and last name fields
        //to be editable
        tableView.setEditable(true);
        employeeName.setCellFactory(TextFieldTableCell.forTableColumn());
        department.setCellFactory(TextFieldTableCell.forTableColumn());
        designation.setCellFactory(TextFieldTableCell.forTableColumn());
        place.setCellFactory(TextFieldTableCell.forTableColumn());
        mobileNumber.setCellFactory(TextFieldTableCell.forTableColumn());

        // priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        //  amountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
      //  phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
      //  pincodeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

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
    ObservableList<Employee> people;
    /**
     * This method will return an ObservableList of People objects
     */
    public ObservableList<Employee>  getPeople(String query)
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

                String name = st.getString("emp_name");
                String designation=st.getString("designation");
                String department=st.getString("department_name");
                String place=st.getString("emp_place");
                String mobileno=st.getString("phone_no");

                //Integer age = rs.getInt("age");
                //String contact_num = rs.getString("contact_num");
                people.add(new Employee(name,designation,department,place,mobileno));
                System.out.println( "Name: "+name+"Phone: " +mobileno+" Additional: "+department+"City: "+designation);

            }


            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        return people;

    }
}
